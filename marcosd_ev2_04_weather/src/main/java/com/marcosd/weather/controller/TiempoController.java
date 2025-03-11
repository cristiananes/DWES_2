package com.marcosd.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.marcosd.weather.beans.Tiempo;
import com.marcosd.weather.beans.WeatherResponse;
import com.marcosd.weather.service.WeatherService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class TiempoController {

	@Autowired
	private WeatherService ws;
	
	
	// Ruta para mostrar el formulario en "/"
    @GetMapping("/")
    public ModelAndView mostrarFormulario() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("tiempo", new Tiempo()); 
        return mav;
    }	
	
    
    
    
    
    
    
    
    @PostMapping("/procesarTiempo")
    public ModelAndView procesarTiempo(@ModelAttribute Tiempo tiempo) {
        
    	WeatherResponse wr = ws.obtenerClima(tiempo);
    	
        // Retornamos la vista "index" con el objeto tiempo en el modelo
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("weatherResponse", wr);
        return mav;
    }
}
