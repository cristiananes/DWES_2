package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Astronauta;
import com.example.demo.repository.AstronautaRepository;

@Service
public class AstronautaService {
	
	@Autowired
	AstronautaRepository astronautaRepository;
	
	private final int TAMANIO =5;
	public Page<Astronauta> obtenerAstronautasPaginados(int pagina){
		Pageable pageable = PageRequest.of(pagina, TAMANIO);
		return astronautaRepository.findAll(pageable);
	}
	
	
	
}
