package com.example.demo.learner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Astronauta;

@Controller
public class RutasLearner {

	@GetMapping("/hola")
	public ModelAndView rutaBasica() {
		
		Astronauta a =new Astronauta();
		a.setNombre("Marcos");
		a.setNacionalidad("España");
		a.setEdad(35);
		
		ModelAndView salida = new ModelAndView();
		salida.setViewName("astronauta");
		salida.addObject("astronauta", a);
		return salida;
	}
}
