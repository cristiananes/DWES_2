package com.generico.astronautas.servicios;

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
	
	
	public Page<Astronauta> getPage(int pagina) {
		
		Pageable pageable = PageRequest.of(pagina, TAMANIO_PAGINA);
		
		return astronautaRepository.findAll(pageable);
	}
	public Page<Astronauta> getPageOrdered (int pagina, String por){
		Sort sort = Sort.by(por);
		Pageable pageable = PageRequest.of(pagina, TAMANIO_PAGINA, sort);
		return astronautaRepository.findAll(pageable);
	}
	
}
