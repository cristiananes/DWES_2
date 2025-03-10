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

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.generico.astronautas.beans.Astronauta;
import com.generico.astronautas.beans.Mision;
import com.generico.astronautas.beans.Nave;
import com.generico.astronautas.dtos.MisionDTO;
import com.generico.astronautas.repositorios.*;
import com.generico.astronautas.servicios.AstronautaService;
import com.generico.astronautas.servicios.MisionFechasService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/mision")
@Slf4j
public class MisionController {

	@Autowired
	private MisionRepository misionRepository;
	
	@Autowired
	private NaveRepository naveRepository;
	
	
	@Autowired
	private ParticipaRepository participaRepository;
		
	
	@Autowired
	private MisionFechasService mfs;
	
	@GetMapping("/{id}")
	public ModelAndView getMision(@PathVariable Long id) {
		
		ModelAndView salida = new ModelAndView("mision/mision");
		
		if(misionRepository.existsById(id)) {
			salida.addObject("mision", misionRepository.findById(id).get());
			salida.addObject("naves", naveRepository.findAll());
		}
		else salida.setViewName("redirect:/mision");
		

		return salida;
	}
	
	
	@GetMapping("/asignarNave/{idMision}/{idNave}")
	public ModelAndView asignarNave(@PathVariable Long idMision,@PathVariable Long idNave) {
		
		ModelAndView salida = new ModelAndView("redirect:/mision/"+idMision);
		
		Optional<Mision> m = misionRepository.findById(idMision);
		Optional<Nave> n = naveRepository.findById(idNave);
		
		if(m.isPresent() & n.isPresent()) {
			
			m.get().setNave(n.get());
			misionRepository.save(m.get());
		}
		else salida.setViewName("redirect:/mision");
		

		return salida;
	}
	
	
	
	@GetMapping("/desvincularNave/{idMision}")
	public ModelAndView desvincularNave(@PathVariable Long idMision) {
		
		ModelAndView salida = new ModelAndView("redirect:/mision/"+idMision);
		
		Optional<Mision> m = misionRepository.findById(idMision);
		
		if(m.isPresent()) {
			
			m.get().setNave(null);
			misionRepository.save(m.get());
		}
		else salida.setViewName("redirect:/mision");
		

		return salida;
	}
	
	
	@GetMapping // nave
	public ModelAndView getTodasNaves() {
		
		ModelAndView salida = new ModelAndView("mision/misiones");

		List<Mision> lista = misionRepository.findAll();
		List<MisionDTO> listaVista = mfs.getMisionesVista(lista);
		
		salida.addObject("misiones", lista);
		
		
		return salida;
		
	}
	
	
	
	
	@GetMapping("/addMision")
	public ModelAndView addMision() {
		
		ModelAndView salida = new ModelAndView("mision/misionForm");
		salida.addObject("mision", new Mision());
		
		return salida;
	}
	
	
	@GetMapping("/updateMision/{id}")
	public ModelAndView updateNave(@PathVariable Long id) {
		
		ModelAndView salida = new ModelAndView("mision/misionForm");
		
		if(misionRepository.existsById(id)) {
			salida.addObject("mision", misionRepository.findById(id).get());
		}
		else salida.setViewName("redirect:/mision");
		
		return salida;
	}
	
	
	
	
	
	@PostMapping("/saveMision") 
	public String saveAstronauta(@ModelAttribute Mision mision) {
		
		misionRepository.save(mision);
		
		return "redirect:/mision";
		
	}
	
	
	
	
	
	@GetMapping("/delete/{id}")
	@Transactional
	public String deleteMision(@PathVariable Long id) {
		
		Optional<Mision> misionOptional = misionRepository.findById(id);
		if(misionOptional.isPresent()) {
			misionRepository.deleteById(id);
		}
		return 	"redirect:/mision";
	}
	
	
	
	
}
