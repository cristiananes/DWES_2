package com.mialquiler.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Contrato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate inicio;
	private LocalDate fin;
	private int precio;
	private boolean estado;
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contrato_propiedad")
	private List<PropiedadContrato> propiedadContratos;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contrato_pago")
	private List<Pago> pagos;
}
