package com.marcosd.weather.beans;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    
	private Weather[] weather;
    private Wind wind;
    private Clouds clouds;
    private String base;
    private int visibility;
    private String name;
    private int cod;
    private Coord coord;
    private Main main;
}









