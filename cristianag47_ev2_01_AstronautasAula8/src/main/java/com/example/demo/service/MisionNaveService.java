package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Mision;
import com.example.demo.entity.NaveEspacial;
import com.example.demo.repository.MisionRepository;
import com.example.demo.repository.NaveEspacialRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MisionNaveService {

	@Autowired NaveEspacialRepository naveEspacialRepository;
	@Autowired MisionRepository misionRepository;
	
	public void asignarMisionNave(Long naveId, Long misionId) {
		log.info("recibidos naveId: "+naveId+ " y misionId: " +misionId);
		Optional<NaveEspacial> nave = naveEspacialRepository.findById(naveId);
		Optional<Mision> mision = misionRepository.findById(misionId);
		
		NaveEspacial naveObtenida = nave.get();
		Mision misionObtenida = mision.get();
		
		log.info("en el service: optional de nave: "+naveObtenida+" Optional de mision: "+misionObtenida);
		
		
		if(nave.isPresent()) {
			if(mision.isPresent()) {
				 misionObtenida.setNave(naveObtenida);
				 misionRepository.save(misionObtenida);
			}else {
				log.info("La mision no se encontro");
			}
		}else {
			log.info("La nave no se encontro");
		}
	}
}
