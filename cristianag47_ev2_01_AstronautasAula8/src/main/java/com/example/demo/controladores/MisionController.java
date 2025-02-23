package com.example.demo.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Mision;
import com.example.demo.entity.NaveEspacial;
import com.example.demo.repository.MisionRepository;
import com.example.demo.repository.NaveEspacialRepository;
import com.example.demo.service.MisionFechaService;
import com.example.demo.service.MisionNaveService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/mision")
@Slf4j
public class MisionController {

	
	@Autowired MisionRepository misionRepository;
	@Autowired MisionFechaService misionFechaService;
	@Autowired MisionNaveService misionNaveService;
	@Autowired NaveEspacialRepository naveEspacialRepository;
	
	//ruta para traer todas las misiones
	
	@GetMapping("/lista")
	public ModelAndView obtenerMisiones() {
		ModelAndView salida = new ModelAndView("/misiones/misiones");
		salida.addObject("misiones", misionFechaService.misionesFechadas());
		return salida;
	}
	
	//ruta para buscar 1 mision
	@GetMapping("/{id}")
	public ModelAndView obtenerMision(@PathVariable Long id) {
		ModelAndView salida = new ModelAndView("/misiones/mision");
		Optional<Mision> misionOptional = misionRepository.findById(id);
		salida.addObject("naves",(List<NaveEspacial>)naveEspacialRepository.findAll());
		if (misionOptional.isPresent()) {
			salida.addObject("mision", misionOptional.get());
			
		}
		return salida;
	}
	//ruta para eliminar misiones
	@GetMapping("/eliminar/{id}")
    public ModelAndView eliminarMision(@PathVariable Long id) {
        ModelAndView salida = new ModelAndView("redirect:/mision/lista");
        misionRepository.deleteById(id);
        return salida;
    }
	
	//ruta para crear misiones
	@GetMapping("/add")
	public ModelAndView addMision() {
		ModelAndView salida = new ModelAndView("/misiones/misionForm");
		salida.addObject("mision", new Mision());
		return salida;
	}
	
	//ruta para actualizar las misiones
	@GetMapping("/update/{id}")
	public ModelAndView updateMision(@PathVariable Long id) {
		ModelAndView salida = new ModelAndView("/misiones/misionForm");
		Optional<Mision> m = misionRepository.findById(id);
		
		//El siguiente log causaba un error cuando la nave tenia mision 
		//porque llamabas rebundantemente 
		//log.info("mision obtenida:"+m.get());
		
		try {
			if (m.isPresent()) {
				
				//no se supone que m.get() ya trae la mision tenga nave o no?
	            salida.addObject("mision", m.get());
	            
	        }
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error al actualizar misión", e);
			salida.setViewName("redirect:/mision/lista");
			
		}
        return salida;
	}
	
	//ruta para guardar las misiones
	@PostMapping("/save")
	public String guardarMision (@ModelAttribute Mision mision) {
		try {
			misionRepository.save(mision);
		} catch (Exception e) {
			log.info("no se ha podido guardar la mision por: "+e);
		}
		
		return "redirect:/mision/lista";
	}
	
	//ruta para asignar nave a mision
	@GetMapping("/asignar/{naveId}/{misionId}")
	public String asignarNave(@PathVariable Long naveId, @PathVariable Long misionId) {
		try {
			misionNaveService.asignarMisionNave(naveId, misionId);
			
		} catch (Exception e) {
			return "Ha ocurrido el siguient error: "+e;
		}
		return "redirect:/mision/"+misionId ;
		
	}
	
	//ruta para quitar nave de mision
	
	@GetMapping("/desasignar/{misionId}")
	public String desasignarNave( @PathVariable Long misionId) {
		
		try {
			
			Optional<Mision> mision = misionRepository.findById(misionId);
			mision.get().setNave(null);
			misionRepository.save(mision.get());
			return "redirect:/mision/"+mision.get().getId();
		} catch (Exception e) {
			return "Ha ocurrido el siguiente error: "+e;
		}
		
	}
	
}
