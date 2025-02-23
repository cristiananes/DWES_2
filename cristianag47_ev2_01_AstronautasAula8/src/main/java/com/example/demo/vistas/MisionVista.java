package com.example.demo.vistas;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.demo.entity.NaveEspacial;

import lombok.Data;

@Data
public class MisionVista {

	private Long id;
	private String nombre;
	
	private String fechaInicio;
	private String fechaFin;
	private NaveEspacial nave;
	
//	public void setFecha_inicio(LocalDate fechaInicio) {
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//		this.fecha_inicio = fechaInicio.format(formatter);
//	}
//	
//	public void setFecha_fin(LocalDate fechaFin) {
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//		this.fecha_fin = fechaFin.format(formatter);
//	}
}
