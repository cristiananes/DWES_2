package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Mision;

public interface MisionRepository extends CrudRepository<Mision, Long> {

	List<Mision> findByNaveIsNull();
	@Query("SELECT m FROM Mision m LEFT JOIN FETCH m.nave WHERE m.id = :id")
	Optional<Mision> findByIdWithNave(@Param("id") Long id);

}
