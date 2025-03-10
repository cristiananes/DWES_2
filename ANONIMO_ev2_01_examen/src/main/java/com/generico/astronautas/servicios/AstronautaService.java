package com.generico.astronautas.servicios;

import java.util.HashMap;
import java.util.Map;

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
		
		return astronautaRepository.findAll(pageable);
	}
	
}
