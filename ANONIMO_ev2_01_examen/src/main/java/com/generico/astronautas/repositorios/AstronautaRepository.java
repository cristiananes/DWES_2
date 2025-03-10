package com.generico.astronautas.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.generico.astronautas.beans.Astronauta;

public interface AstronautaRepository extends CrudRepository<Astronauta,Long> {

	public Page<Astronauta> findAll(Pageable pageable);
	
}
