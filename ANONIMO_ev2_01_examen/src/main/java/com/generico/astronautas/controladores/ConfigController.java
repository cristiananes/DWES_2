package com.generico.astronautas.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.generico.astronautas.beans.Astronauta;
import com.generico.astronautas.repositorios.*;
import com.generico.astronautas.servicios.AstronautaService;

@Controller
@RequestMapping("/config")
public class ConfigController {


	
	@GetMapping("/idioma")
	public String getConfigIdioma() {
		
		return "configuracion/idioma";
	}
	
	
	@GetMapping("/paginas")
	public String getConfigPaginas() {
		
		return "configuracion/tamaniopaginas";
	}
	
	
	@GetMapping("/borrados")
	public String getConfigBorrados() {
		
		return "configuracion/borrados";
	}
	
}
