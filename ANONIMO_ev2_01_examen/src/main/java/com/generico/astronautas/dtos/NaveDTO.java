package com.generico.astronautas.dtos;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NaveDTO {

	
	private Long id;
	
	@NotEmpty(message="no puede estar vacio")
	@Size(min=5, max=20, message="longitud esperada entre 5 y 20 caracteres")
	private String nombre;
	
	private int capacidad;
	private List<Long> misiones = new ArrayList<Long>();
	
	
	
	
}
