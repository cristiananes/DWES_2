package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
	Optional<Usuario> findById(String usuario);
	List<Usuario> findAll();
	//usuario directamente sin optional
	Usuario getById(String usuario);
}
