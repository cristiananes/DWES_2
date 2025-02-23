package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Participa;
import com.example.demo.entity.ParticipaId;

public interface ParticipaRepository extends CrudRepository<Participa, ParticipaId>{

}
