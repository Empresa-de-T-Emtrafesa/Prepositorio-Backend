package com.emtrafesa.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@Table(name = "ruta", indexes = {
        @Index(name = "idx_origen_destino", columnList = "origen, destino")
})

public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "origen", nullable = false)
    private String origen;

    @Column(name = "destino", nullable = false)
    private String destino;

    @Column(name = "tiene_escalas", nullable = false)
    private Boolean tieneEscalas;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;
}
