package com.generico.astronautas.beans;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;


@Entity
@Data
public class Nave {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private int capacidad;
	
	
	@OneToMany(mappedBy = "nave", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Mision> misiones = new ArrayList<Mision>();
}
