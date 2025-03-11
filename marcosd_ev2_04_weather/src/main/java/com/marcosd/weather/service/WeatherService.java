package com.marcosd.weather.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.marcosd.weather.beans.Tiempo;
import com.marcosd.weather.beans.WeatherResponse;

import lombok.Data;

@Service
public class WeatherService {

    private static final String API_KEY = "b32ee28be9c982ca8f90f3aea9393bb7";
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";
    
    
    public WeatherResponse obtenerClima(Tiempo tiempo) {
        
    	RestTemplate restTemplate = new RestTemplate();
    	
    	String url = WEATHER_URL+"?q="
    			+tiempo.getCiudad()+","+tiempo.getPais()
    			+ "&appid="+API_KEY+"&units=metric";
    	
    	WeatherResponse wr =restTemplate.getForObject(url, WeatherResponse.class);
    	return wr;
    	
    }
}



