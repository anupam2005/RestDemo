package com.anupam.camel.RestDemo;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.support.DefaultMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class WeatherRestServiceRoute extends RouteBuilder {

    private WeatherDAO weatherDAO;

    public WeatherRestServiceRoute(WeatherDAO weatherDAO) {
        this.weatherDAO=weatherDAO;
    }

    @Override
    public void configure() throws Exception {

        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.auto);

        //Old implementation
        /*from("rest://get:weather/api/{city}?produces=application/json")
                  .to("direct:getWeather");*/

        rest()
                .consumes(MediaType.APPLICATION_JSON_VALUE).produces(MediaType.APPLICATION_JSON_VALUE)
                .get("api/weather/{city}").outType(WeatherDTO.class).to("direct:getWeather")
                .post("api/weather").type(WeatherDTO.class).to("direct:setWeather");

          from("direct:getWeather")
                  .process(this::getWeatherByCity);

          from("direct:setWeather")
                  .log("BODY: ${body}")
                  .process(this::setWeatherByCity);
    }

    private void setWeatherByCity(Exchange exchange) {
        System.out.println("exchange.getIn().getBody(WeatherDTO.class)-----"+exchange.getMessage().getBody(WeatherDTO.class));
        WeatherDTO weatherDTO1 = exchange.getMessage().getBody(WeatherDTO.class);
        this.weatherDAO.setWeatherDTO(weatherDTO1);

        Message message = new DefaultMessage(exchange.getContext());
        message.setBody(weatherDTO1);
        exchange.setMessage(message);
    }

    private void getWeatherByCity(Exchange exchange) {

        String city = exchange.getMessage().getHeader("city",String.class).toUpperCase();
        WeatherDTO weatherDTO=weatherDAO.getWeatherDTOByCity(city);

        if(!Objects.equals(weatherDTO,null)) {
            Message message = new DefaultMessage(exchange.getContext());
            message.setBody(weatherDTO);
            exchange.setMessage(message);
        }
        else{
            exchange.getMessage().setHeader(exchange.HTTP_RESPONSE_CODE, HttpStatus.NOT_FOUND.value());
            exchange.getMessage().setBody(weatherDTO);
        }
    }
}
