package com.anupam.camel.RestDemo;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class WeatherDAO {

    private static Map<String,WeatherDTO> weatherDtoMap=new HashMap<String,WeatherDTO>();

    public WeatherDAO() {
        weatherDtoMap.put("DELHI",new WeatherDTO("Delhi",20.3,12.3,new Date()));
        weatherDtoMap.put("BOMBAY",new WeatherDTO("Bombay",24.3,11.3,new Date()));
        weatherDtoMap.put("CALCUTTA",new WeatherDTO("Calcutta",30.3,16.3,new Date()));
        weatherDtoMap.put("MADRAS",new WeatherDTO("Madras",33.3,15.3,new Date()));
    }

    public WeatherDTO getWeatherDTOByCity(String city) {
        return weatherDtoMap.get(city);
    }

    public void setWeatherDTO(WeatherDTO weatherDTO){
        String city=weatherDTO.getCity().toUpperCase();
        weatherDTO.setRequestDate(new Date());
        weatherDtoMap.put(city,weatherDTO);
    }
}
