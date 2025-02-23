package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pago {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//acuerdate lo que viste en el video que a los nombres compuestos si te los guarda mal igual tienes que ponerle el 
	//@Column(name = "fecha_prevista")
	private LocalDate fechaPrevista;
	private LocalDate fechaReal;
	private int cantidadEsperada;
	private int cantidadAbonada;
	private int retraso;
	
	@Enumerated
	private EstadoPago estadoPago;
	
	
	
}
