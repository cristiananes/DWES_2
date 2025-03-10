package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

}
