package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/ciudad")
public class CiudadController {

	@GetMapping ("/{ciudad}/{cp}")
	public ModelAndView obtenerTiempo(@PathVariable String ciudad,
										@PathVariable int cp) {
		
		
		return null;
	}
}
