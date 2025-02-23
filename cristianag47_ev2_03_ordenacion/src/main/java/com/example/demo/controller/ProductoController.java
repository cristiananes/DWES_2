package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Producto;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.ProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    @Autowired ProductoRepository productoRepository;

    @GetMapping
    public String listarProductos(Model model,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "orden", defaultValue = "id,asc") String orden) {

        // Llamamos al servicio para obtener la página de productos ordenados dinámicamente
        Page<Producto> productosPage = productoService.productosOrdenados(page, orden);
        model.addAttribute("productosPage", productosPage);
        model.addAttribute("orden", orden); // lo usamos para mantener el criterio de ordenación en la vista
        return "productos/productos"; // nombre de la plantilla (productos.html en src/main/resources/templates)
    }
    @GetMapping("/filtrar")
    public String filtrarNombre(Model model,
    							@RequestParam(value = "nombre", defaultValue ="producto")  String nombre) {
    	Pageable pageable = PageRequest.of(0, 5);
    	List<Producto> productosPage = productoRepository.findAllByNombre(nombre, pageable );
    	model.addAttribute("productos", productosPage);
    	return "productos/porNombre";
    }
    							
}
