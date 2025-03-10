package com.generico.astronautas.beans;


import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;


@Entity
@Data
public class Mision {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha_inicio;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha_fin;
	
	
	@ManyToOne
	@JoinColumn(name="id_nave",nullable = true)
	private Nave nave;
	
	
	
	@OneToMany(mappedBy = "mision")
	private List<Participa> participaciones; 
	
}
