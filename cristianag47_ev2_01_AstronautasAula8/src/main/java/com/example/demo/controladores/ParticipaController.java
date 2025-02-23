package com.example.demo.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Astronauta;
import com.example.demo.entity.Mision;
import com.example.demo.entity.Participa;
import com.example.demo.entity.ParticipaId;
import com.example.demo.repository.AstronautaRepository;
import com.example.demo.repository.MisionRepository;
import com.example.demo.repository.ParticipaRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ParticipaController {

	@Autowired ParticipaRepository participaRepository;
	@Autowired MisionRepository misionRepository;
	@Autowired AstronautaRepository astronautaRepository;
	@GetMapping("/participa")
	public ModelAndView listarParticipaciones(@RequestParam Optional<Integer> error) {
	    ModelAndView salida = new ModelAndView("participa/participa");
	    
	    
	    //pregunto oye estan intentando abrirme esta ruta con un parametro adicional? es un errror? ese error vale 1? vale cuelo la ruta 
	    //con ese menasaje dde error
	    if (error.isPresent() && error.get().intValue()==1) {
	    	salida.addObject("error_message", "fallo en la inserccion por la duplicidad de claves");
	    }
	    List<Participa> participaciones = (List<Participa>)participaRepository.findAll();
	    salida.addObject("participaciones", participaciones);
	    return salida;
	}
	
	@GetMapping("/participa/add")
	public ModelAndView mostrarFormulario() {
	    ModelAndView salida = new ModelAndView("participa/participaForm");
	    salida.addObject("participa", new Participa());
	    salida.addObject("astronautas", astronautaRepository.findAll());
	    salida.addObject("misiones", misionRepository.findAll());
	    return salida;
	}

	@PostMapping("/participa/save")
	public ModelAndView guardarParticipacion(@ModelAttribute Participa participa,
	                                         RedirectAttributes redirectAttributes) {

	    
	    ModelAndView salida = new ModelAndView("redirect:/participa");

	    try {
	        if (participa.getId_participa() == null) {
	          
	            redirectAttributes.addFlashAttribute("error_message", "Error: Identificadores nulos.");
	            return salida;
	        }

	        Long idAstronauta = participa.getId_participa().getIdAstronauta();
	        Long idMision = participa.getId_participa().getIdMision();

	      

	        Optional<Astronauta> astronautaOptional = astronautaRepository.findById(idAstronauta);
	        Optional<Mision> misionOptional = misionRepository.findById(idMision);

	        if (astronautaOptional.isPresent() && misionOptional.isPresent()) {
	            Astronauta astronauta = astronautaOptional.get();
	            Mision mision = misionOptional.get();

	            // **💡 Asegurar que las entidades están correctamente asignadas antes de guardar**
	            participa.setAstronauta(astronauta);
	            participa.setMision(mision);

	            

	            participaRepository.save(participa);
	            
	        } else {
	           
	            redirectAttributes.addFlashAttribute("error_message", "Error: Astronauta o misión no encontrados.");
	            return new ModelAndView("redirect:/participa");
	        }
	    } catch (DataIntegrityViolationException e) {
	        
	        redirectAttributes.addFlashAttribute("error_message", "Hubo un problema al guardar las claves: " + e);
	    } catch (Exception e) {
	        
	        redirectAttributes.addFlashAttribute("error_message", "Error inesperado: " + e);
	    }

	    return salida;
	}

	
	@GetMapping("/participa/eliminar/{astronautaId}/{misionId}/{rol}")
	public String eliminarParticipacion(@PathVariable Long astronautaId, 
	                                    @PathVariable Long misionId, 
	                                    @PathVariable String rol) { 
	    ParticipaId pId = new ParticipaId(astronautaId, misionId, rol);
	    participaRepository.deleteById(pId);
	    return "redirect:/participa";
	}

}
