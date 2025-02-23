package com.example.demo.entity;


import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "participaciones")
public class Mision {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaInicio;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaFin;
	
	@ManyToOne
	@JoinColumn(name = "nave_id", nullable = true)
	private NaveEspacial nave;
	
	@OneToMany(mappedBy = "mision",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL)
	private List<Participa> participaciones;
	
//	@DateTimeFormat(pattern = "yyyy-mm-dd")
//	private LocalDate fechaFin;
	
}
