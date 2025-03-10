package com.generico.astronautas.beans;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Participa {

    @EmbeddedId
    private ParticipaId id;

    @ManyToOne
    @MapsId("astronautaId") // Enlaza astronautaId con el ID del Astronauta
    @JoinColumn(name = "astronauta_id", referencedColumnName = "id", nullable = false) 
    private Astronauta astronauta;

    @ManyToOne
    @MapsId("misionId") // Enlaza misionId con el ID de la Misión
    @JoinColumn(name = "mision_id", referencedColumnName = "id", nullable = false) 
    private Mision mision;

    // Otros atributos de la relación si son necesarios
    @Column(nullable = false)
    private String descripcionTarea; // Detalles de la participación
}