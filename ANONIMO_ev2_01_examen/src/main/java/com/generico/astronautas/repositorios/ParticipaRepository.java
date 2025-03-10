package com.generico.astronautas.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.generico.astronautas.beans.Astronauta;
import com.generico.astronautas.beans.Mision;
import com.generico.astronautas.beans.Nave;
import com.generico.astronautas.beans.Participa;
import com.generico.astronautas.beans.ParticipaId;

public interface ParticipaRepository extends JpaRepository<Participa,ParticipaId> {

	void deleteByMision(Mision mision);
}
