package com.example.demo.DTO;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NaveDTO {

	private Long id;
	
	private String nombre;
	private int capacidad;
	private List<Long> misiones = new ArrayList<Long>();
}
