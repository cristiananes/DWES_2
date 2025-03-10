package com.generico.astronautas.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.generico.astronautas.beans.Astronauta;
import com.generico.astronautas.beans.Mision;
import com.generico.astronautas.beans.Nave;

public interface MisionRepository extends JpaRepository<Mision,Long> {
	
	List<Mision> findByNaveIsNull();

}
