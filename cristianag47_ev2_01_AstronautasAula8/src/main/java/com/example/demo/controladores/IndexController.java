package com.example.demo.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@GetMapping("/")
	public ModelAndView paginaIncio() {
		ModelAndView salida = new ModelAndView("index");
		return salida;
	}
}
