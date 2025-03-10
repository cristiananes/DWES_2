package com.generico.astronautas.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.generico.astronautas.beans.Astronauta;
import com.generico.astronautas.beans.Nave;

public interface NaveRepository extends CrudRepository<Nave,Long> {

}
