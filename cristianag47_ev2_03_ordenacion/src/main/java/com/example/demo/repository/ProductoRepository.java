package com.example.demo.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
	List<Producto> findAllByPrecio(double precio, Pageable pagebale);
	List<Producto> findAllByNombre(String nombre, Pageable pageable);
}
