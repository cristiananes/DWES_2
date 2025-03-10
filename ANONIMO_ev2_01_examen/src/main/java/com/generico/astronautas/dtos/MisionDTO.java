package com.generico.astronautas.dtos;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
public class MisionDTO {


	private Long id;
	private String nombre;
	
    private String fecha_inicio;
    private String fecha_fin;

    // Getters y setters
    public void setFecha_inicio(LocalDate fechaInicio) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MM 'del año' yy");
        this.fecha_inicio = fechaInicio.format(formatter);
    }

    public void setFecha_fin(LocalDate fechaFin) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.fecha_fin = fechaFin.format(formatter);
    }
}
