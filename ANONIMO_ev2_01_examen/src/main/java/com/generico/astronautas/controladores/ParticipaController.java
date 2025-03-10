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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLIntegrityConstraintViolationException;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;

import com.generico.astronautas.beans.Astronauta;
import com.generico.astronautas.beans.Participa;
import com.generico.astronautas.beans.ParticipaId;
import com.generico.astronautas.repositorios.*;
import com.generico.astronautas.servicios.AstronautaService;
import com.generico.astronautas.servicios.ParticipaService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/participa")
@Slf4j
public class ParticipaController {

	@Autowired
	private ParticipaRepository participaRepository;
	
	@Autowired
	private ParticipaService participaService;
	
	@Autowired
	private MisionRepository misionRepository;
	
	@Autowired
	private AstronautaRepository astronautaRepository;
	
	
	
	@GetMapping
	public ModelAndView getTodosParticipa(@RequestParam Optional<Integer> error) {
		
		ModelAndView salida = new ModelAndView("participa/participa");
		
		salida.addObject("participas", participaRepository.findAll());
		salida.addObject("astronautas", astronautaRepository.findAll());
		salida.addObject("misiones", misionRepository.findAll());
		salida.addObject("participa",new Participa());
		
		return salida;
	}
	
	
	
	
	
	
	
	
	
	@PostMapping("/saveParticipa") 
	public ModelAndView saveAstronauta(@ModelAttribute Participa participa, 
								RedirectAttributes redirectAttributes) {
		
		ModelAndView salida = new ModelAndView("redirect:/participa");
		try {
		//if(!participaRepository.existsById(participa.getId())) {
			participaRepository.save(participa);
		}
		//else {
		catch(DataIntegrityViolationException e) {
			
			//log.error(""+e.getMessage());
			//salida.setViewName("redirect:/participa?error=1");
			redirectAttributes.addFlashAttribute("error_message", "Hubo un problema al guardar: " + e.getMessage());
		}
		
		return salida;
		
	}
	
	
	
	
	
	@GetMapping("/delete/{idMision}/{idAstronauta}/{rol}")
	public String deleteParticipa(@PathVariable Long idMision, @PathVariable Long idAstronauta,@PathVariable String rol) {
		
		participaRepository.deleteById(participaService.getParticipaId(idMision,idAstronauta,rol));
		
		return 	"redirect:/participa";
	}
	
	
	
	
}
