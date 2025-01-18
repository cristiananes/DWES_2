package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Astronauta;

public interface AstronautaRepository extends CrudRepository<Astronauta, Long> {

	Page<Astronauta> findAll(Pageable pageable);
}
