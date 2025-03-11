package com.generico.astronautas.servicios;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.Map;

=======
>>>>>>> 03eaf5eca47467f8cb5cdaa8a46f9ec52a535592
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.generico.astronautas.beans.Astronauta;
import com.generico.astronautas.repositorios.AstronautaRepository;

import org.springframework.data.domain.Page;

@Service
public class AstronautaService {

	@Autowired
	private AstronautaRepository astronautaRepository;
	
	private final int TAMANIO_PAGINA = 3;
<<<<<<< HEAD
	private Boolean actualDirection;
	private String orden;
	
	public Page<Astronauta> getPage(int pagina) {
		
		Sort ordenacion = Sort.by("edad").ascending();
		Pageable pageable = PageRequest.of(pagina, TAMANIO_PAGINA, ordenacion);
		
		return astronautaRepository.findAll(pageable);
	}
	
	//mapa para almacenar ultimo ordenamiento
	
	public Page<Astronauta> getPageOrdered(int pagina, String por){
		if (actualDirection == null || actualDirection == false ) {
			actualDirection = true;

		}else if (actualDirection == true) { 
			actualDirection=false;
		}
		if(actualDirection == true) {
			orden = "ASC";
		}else if(actualDirection==false) {
			orden="DESC";
		}
		Sort.Direction direction = Sort.Direction.fromString(orden);
		Sort ordenacion = Sort.by(direction, por);
		Pageable pageable = PageRequest.of(pagina, TAMANIO_PAGINA, ordenacion);
		

	
	
	public Page<Astronauta> getPage(int pagina) {
		
		Pageable pageable = PageRequest.of(pagina, TAMANIO_PAGINA);
		
		return astronautaRepository.findAll(pageable);
	}
	public Page<Astronauta> getPageOrdered (int pagina, String por){
		Sort sort = Sort.by(por);
		Pageable pageable = PageRequest.of(pagina, TAMANIO_PAGINA, sort);
>>>>>>> 03eaf5eca47467f8cb5cdaa8a46f9ec52a535592
		return astronautaRepository.findAll(pageable);
	}
	
}
