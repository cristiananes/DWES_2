package com.example.demo.controladores;

import java.util.Optional;

import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.DTO.NaveDTO;
import com.example.demo.entity.Mision;
import com.example.demo.entity.NaveEspacial;
import com.example.demo.mapper.NaveMapper;
import com.example.demo.repository.MisionRepository;
import com.example.demo.repository.NaveEspacialRepository;
import com.example.demo.service.MisionNaveService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/nave")
@Slf4j
public class NaveController {

	@Autowired NaveEspacialRepository naveEspacialRepository;
	@Autowired MisionRepository misionRepository;
	@Autowired MisionNaveService misionNaveService;
	@Autowired NaveMapper naveMapper;
	//ruta para traer todas las naves
	
		@GetMapping("/lista")
		public ModelAndView obtenerMisiones() {
			ModelAndView salida = new ModelAndView("/naves/naves");
			salida.addObject("naves", naveEspacialRepository.findAll());
			return salida;
		}
		
		//ruta para buscar 1 nave
		@GetMapping("/{id}")
		public ModelAndView obtenerMision(@PathVariable Long id) {
			ModelAndView salida = new ModelAndView("/naves/nave");
			salida.addObject("misiones",misionRepository.findByNaveIsNull() );
			Optional<NaveEspacial> naveOptional = naveEspacialRepository.findById(id);
			if (naveOptional.isPresent()) {
				salida.addObject("misiones", misionRepository.findByNaveIsNull());
				salida.addObject("nave", naveOptional.get());
				
			}
			return salida;
		}
		//ruta para eliminar naves
		@GetMapping("/eliminar/{id}")
	    public ModelAndView eliminarNave(@PathVariable Long id) {
	        ModelAndView salida = new ModelAndView("redirect:/nave/lista");
	        naveEspacialRepository.deleteById(id);
	        return salida;
	    }
		
		//ruta para crear naves
		@GetMapping ("/add")
		public ModelAndView crearNave () {
			ModelAndView salida = new ModelAndView("/naves/naveForm");
			salida.addObject("nave", new NaveEspacial());
			return salida;		
		}
		//ruta para crear navesDTO
		@GetMapping ("/dto/add")
		public ModelAndView crearNaveDTO () {
			ModelAndView salida = new ModelAndView("/naves/naveFormdto");
			salida.addObject("nave", new NaveEspacial());
			return salida;		
		}
		
		//ruta para guardar naves
		@PostMapping ("/save")
		public String guardarNave(@ModelAttribute NaveEspacial nave) {
			try {
				naveEspacialRepository.save(nave);
			} catch (Exception e) {
				log.info("no se ha podido guardar la nave por: "+e);
			}
			return "redirect:/nave/lista";
		}
		//ruta para guardar navesDTO
				@PostMapping ("/saveNaveDTO")
				public String guardarNaveDTO(@ModelAttribute("nave") NaveDTO nave) {
					try {
						
						naveEspacialRepository.save(naveMapper.naveDTO2Nave(nave));
					} catch (Exception e) {
						log.info("no se ha podido guardar la nave por: "+e);
					}
					return "redirect:/nave/lista";
				}
		
		//ruta para actualizar la naves
		@GetMapping ("/update/{id}")
		public ModelAndView actualizarNave(@PathVariable Long id) {
			ModelAndView salida = new ModelAndView("/naves/naveForm");
			Optional<NaveEspacial> nave = naveEspacialRepository.findById(id);
			if (nave.isPresent()) {
				salida.addObject("nave", nave);
			}else {
				salida.setViewName("/misiones/mision/lista");
			}
			return salida;
		}
		
		//ruta para asignar nave a mision
		@GetMapping("/asignar/{naveId}/{misionId}")
		public String asignarNave(@PathVariable Long naveId, @PathVariable Long misionId) {
			try {
				misionNaveService.asignarMisionNave(naveId, misionId);
				
			} catch (Exception e) {
				return "Ha ocurrido el siguient error: "+e;
			}
			return "redirect:/nave/lista" ;
			
		}
		
		//ruta para quitar nave de mision
		
		@GetMapping("/desasignar/{misionId}")
		public String desasignarNave( @PathVariable Long misionId) {
			
			try {
				
				Optional<Mision> mision = misionRepository.findById(misionId);
				NaveEspacial naveBorrada = mision.get().getNave();
				mision.get().setNave(null);
				misionRepository.save(mision.get());
				return "redirect:/nave/"+naveBorrada.getId();
			} catch (Exception e) {
				return "Ha ocurrido el siguiente error: "+e;
			}
			
			
		}
		
		//ruta para buscar 1 nave
				@GetMapping("/dto/{id}")
				public ModelAndView obtenerMisionDTO(@PathVariable Long id) {
					
					ModelAndView salida = new ModelAndView("/naves/navedto");
					
					if (naveEspacialRepository.existsById(id)) {
						salida.addObject("nave", naveMapper.nave2NaveDTO(naveEspacialRepository.findById(id).get()));
					}
					else salida.setViewName("redirect:/nave");
					return salida;
				}
		
		

}
