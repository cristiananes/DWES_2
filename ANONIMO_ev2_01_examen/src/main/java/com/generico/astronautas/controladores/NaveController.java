package com.generico.astronautas.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
import com.generico.astronautas.beans.Mision;
import com.generico.astronautas.beans.Nave;
import com.generico.astronautas.dtos.NaveDTO;
import com.generico.astronautas.mappers.NaveMapper;
import com.generico.astronautas.repositorios.*;
import com.generico.astronautas.servicios.AstronautaService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/nave")
@Slf4j
public class NaveController {

	@Autowired
	private NaveRepository naveRepository;
	
	@Autowired
	private MisionRepository misionRepository;
	
	@Autowired
	private NaveMapper naveMapper;
	
	@GetMapping("/{id}")
	public ModelAndView getMethodName(@PathVariable Long id) {
		
		ModelAndView salida = new ModelAndView("nave/nave");
		
		if(naveRepository.existsById(id)) {
			salida.addObject("nave", naveRepository.findById(id).get());
			salida.addObject("misionesSinNave",misionRepository.findByNaveIsNull());
		}
		else salida.setViewName("redirect:/nave");
		
		return salida;
	}
	
	

	@GetMapping("/dto/{id}")
	public ModelAndView getnaveDTO(@PathVariable Long id) {
		
		ModelAndView salida = new ModelAndView("nave/navedto");
		
		if(naveRepository.existsById(id)) {
			salida.addObject("nave", naveMapper.nave2NaveDTO(naveRepository.findById(id).get()));
		}
		else salida.setViewName("redirect:/nave");
		
		return salida;
	}	
	
	
	
	
	@GetMapping // nave
	public ModelAndView getTodosAstronautas() {
		
		ModelAndView salida = new ModelAndView("nave/naves");

		Iterable<Nave> lista = naveRepository.findAll();
		salida.addObject("naves", lista);
		
		
		return salida;
		
	}
	
	@GetMapping("/asignarNave/{idMision}/{idNave}")
	public ModelAndView asignarNave(@PathVariable Long idMision,@PathVariable Long idNave) {
		
		ModelAndView salida = new ModelAndView("redirect:/nave/"+idNave);
		
		Optional<Mision> m = misionRepository.findById(idMision);
		Optional<Nave> n = naveRepository.findById(idNave);
		
		if(m.isPresent() & n.isPresent()) {
			
			m.get().setNave(n.get());
			misionRepository.save(m.get());
		}
		else salida.setViewName("redirect:/naves");
		

		return salida;
	}
	
	
	@GetMapping("/desvincularNave/{idMision}/{idNave}")
	public ModelAndView desvincularNave(@PathVariable Long idMision,@PathVariable Long idNave) {
		
		ModelAndView salida = new ModelAndView("redirect:/nave/"+idNave);
		
		Optional<Mision> m = misionRepository.findById(idMision);
		
		if(m.isPresent()) {
			
			m.get().setNave(null);
			misionRepository.save(m.get());
		}
		else salida.setViewName("redirect:/naves");
		

		return salida;
	}	
	
	
	@GetMapping("/addNave")
	public ModelAndView addNave() {
		
		ModelAndView salida = new ModelAndView("nave/naveForm");
		salida.addObject("nave", new Nave());
		
		return salida;
	}
	
	
	
	
	@GetMapping("/updateNaveDto/{id}")
	public ModelAndView updateNaveDt0(@PathVariable Long id) {
		
		ModelAndView salida = new ModelAndView("nave/naveFormdto");
		
		if(naveRepository.existsById(id)) {
			salida.addObject("nave", naveMapper.nave2NaveDTO(naveRepository.findById(id).get()));
		}
		else salida.setViewName("redirect:/nave");
		
		return salida;
	}
	
	
	
	
	@GetMapping("/updateNave/{id}")
	public ModelAndView updateNave(@PathVariable Long id) {
		
		ModelAndView salida = new ModelAndView("nave/naveForm");
		
		if(naveRepository.existsById(id)) {
			salida.addObject("nave", naveRepository.findById(id).get());
		}
		else salida.setViewName("redirect:/nave");
		
		return salida;
	}
	
	
	
	
	
	@PostMapping("/saveNave") 
	public String saveNave(@ModelAttribute Nave nave) {
		
		naveRepository.save(nave);
		
		return "redirect:/nave";
		
	}
	
	
	
	@PostMapping("/saveNaveDTO") 
	public ModelAndView saveNaveDTO(@Valid @ModelAttribute("nave") NaveDTO nave,
			BindingResult bindingResult) {
		
		ModelAndView salida = new ModelAndView("redirect:/nave");
		if(bindingResult.hasErrors()) {
			salida.setViewName("nave/naveFormdto");
		}
		
		return salida;
		
	}	
	
	
	
	
	
	@GetMapping("/delete/{id}")
	public String deleteNave(@PathVariable Long id) {
		
		naveRepository.deleteById(id);
		
		return 	"redirect:/nave";
	}
	
	
	
	
}
