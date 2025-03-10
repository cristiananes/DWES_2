package com.generico.astronautas.beans;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ParticipaId implements Serializable {
    
	private Long astronautaId;
    private Long misionId;
    private String rol; 
}