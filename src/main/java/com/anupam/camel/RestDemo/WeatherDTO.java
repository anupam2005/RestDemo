package com.anupam.camel.RestDemo;

import java.io.Serializable;
import java.util.Date;

public class WeatherDTO implements Serializable {

    private String city;
    private double temperature;
    private double humidity;
    private Date requestDate;

    public WeatherDTO(String city, double temperature, double humidity, Date requestDate) {
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
        this.requestDate = requestDate;
    }

    public WeatherDTO() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    @Override
    public String toString() {
        return "WeatherDTO{" +
                "city='" + city + '\'' +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", requestDate=" + requestDate +
                '}';
    }
}
