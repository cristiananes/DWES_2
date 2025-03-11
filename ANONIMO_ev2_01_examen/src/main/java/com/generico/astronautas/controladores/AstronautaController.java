package com.generico.astronautas.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.boot.Banner.Mode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
=======
import org.springframework.data.domain.Page;
>>>>>>> 03eaf5eca47467f8cb5cdaa8a46f9ec52a535592

import com.generico.astronautas.beans.Astronauta;
import com.generico.astronautas.repositorios.*;
import com.generico.astronautas.servicios.AstronautaService;

@Controller
public class AstronautaController {

	@Autowired
	private AstronautaRepository astronautaRepositorio;
	
	@Autowired
	private AstronautaService astronautaService;
	
	@GetMapping("/astronauta/{id}")
	public ModelAndView getMethodName(@PathVariable Long id) {
		
		ModelAndView salida = new ModelAndView("astronauta/astronauta");
		
		Optional<Astronauta> astronautaOptional = astronautaRepositorio.findById(id);
		if(astronautaOptional.isPresent()) {
			
			salida.addObject("astronauta", astronautaOptional.get());
		}
		
		return salida;
	}
	
	
	
	@GetMapping("/astronauta")
	public ModelAndView getTodosAstronautas() {
		
		ModelAndView salida = new ModelAndView("redirect:/astronauta/pg/0");

<<<<<<< HEAD
		
		
		/*
		 * Iterable<Astronauta> lista = astronautaRepositorio.findAll(pageable);
		 * 
		 * 
		 * salida.addObject("astronautas", lista);
		 */
=======
		/*
		Iterable<Astronauta> lista = astronautaRepositorio.findAll();
		salida.addObject("astronautas", lista);
		*/
>>>>>>> 03eaf5eca47467f8cb5cdaa8a46f9ec52a535592
		return salida;
	}
	
	
	
	@GetMapping("/astronauta/pg/{pagina}")
	public ModelAndView getAstronautasPagina(@PathVariable int pagina) {
		
		ModelAndView salida = new ModelAndView("astronauta/astronautas");

		
		Page<Astronauta> datos = astronautaService.getPage(pagina);
		salida.addObject("hayAnterior", datos.hasPrevious());
		salida.addObject("haySiguiente", datos.hasNext());
		salida.addObject("ultimaPagina", datos.getTotalPages()-1);
		salida.addObject("pagina", pagina+1);

		salida.addObject("astronautas", datos);
		
		return salida;
	}
	
	
	
	
	@GetMapping("/astronauta/addAstronauta")
	public ModelAndView addAstronauta() {
		
		ModelAndView salida = new ModelAndView("astronauta/astronautaForm");
		
		salida.addObject("astronauta", new Astronauta());
		
		return salida;
	}
	
	
	@GetMapping("/astronauta/updateAstronauta/{id}")
	public ModelAndView updateAstronauta(@PathVariable Long id) {
		
		ModelAndView salida = new ModelAndView("astronauta/astronautaForm");
		
		if(astronautaRepositorio.existsById(id)) {
			salida.addObject("astronauta", astronautaRepositorio.findById(id).get());
		}
		else salida.setViewName("redirect:/astronauta");
		
		return salida;
	}
	
	
	
	
	
	@PostMapping("/astronauta/saveAstronauta") 
	public String saveAstronauta(@ModelAttribute Astronauta astronauta) {
		
		astronautaRepositorio.save(astronauta);
		
		return "redirect:/astronauta";
		
	}
	
	
	
	
	
	@GetMapping("/astronauta/delete/{id}")
	public String deleteAstronauta(@PathVariable Long id) {
		
//		try {
			astronautaRepositorio.deleteById(id);
//		}catch(Exception e) {}
		
		return 	"redirect:/astronauta";
	}
	
<<<<<<< HEAD
	
	
	
	
	    @GetMapping("astronauta/ordenar")
	    public ModelAndView ordenarAstronautas(@RequestParam(defaultValue = "edad") String por) {
	   
	    	ModelAndView salida = new ModelAndView("astronauta/astronautas");
	    	salida.addObject("astronautas",astronautaService.getPageOrdered(0, por) );
	    		return salida;
=======
	    @PostMapping("/ordenar")
	    public void ordenarAstronautas(@RequestParam String por) {
	    	
>>>>>>> 03eaf5eca47467f8cb5cdaa8a46f9ec52a535592
	    }

	
	
}
