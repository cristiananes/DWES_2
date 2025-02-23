package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Producto;
import com.example.demo.repository.ProductoRepository;

@Service
public class ProductoService {

	@Autowired ProductoRepository productoRepository;
	
	public Page<Producto> productosOrdenados(int pagina, String orden) {
		//sin orden especifico aun 
		Sort sort = Sort.unsorted();
		if (orden != null && !orden.isEmpty()) {
	        // Se espera que orden tenga el formato "campo,direccion"
	        String[] partes = orden.split(",");
	        if (partes.length == 2) {
	            String campo = partes[0];
	            String direccion = partes[1];
	            if ("desc".equalsIgnoreCase(direccion)) {
	                sort = Sort.by(Sort.Direction.DESC, campo);
	            } else {
	                sort = Sort.by(Sort.Direction.ASC, campo);
	            }
	        }
		}
		Pageable pageable = PageRequest.of(pagina, 5, sort);
		return productoRepository.findAll(pageable);
	}
}
