package com.example.demo.DTO;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.entity.NaveEspacial;

import lombok.Data;

@Data
public class MisionDTO {
	
	private Long id;
	
	private String nombre;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaInicio;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaFin;
	private NaveEspacial nave;
}
