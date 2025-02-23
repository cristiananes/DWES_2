package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Participa {

	@EmbeddedId
	private ParticipaId id_participa;
	
    
	   
	@ManyToOne
	@ToString.Exclude
	private Astronauta astronauta;
	
    @ManyToOne
    @ToString.Exclude
    //, referencedColumnName = "id" (igual lo voy a necesitar para el join column)
    private Mision mision;
	
	private String tarea;
	
	
}
