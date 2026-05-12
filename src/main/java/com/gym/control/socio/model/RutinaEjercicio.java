package com.gym.control.socio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rutina_ejercicio")
public class RutinaEjercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @NotNull(message = "Las series son obligatorias")
    @Column(nullable = false)
    private Integer series;

    @NotNull(message = "Las repeticiones son obligatorias")
    @Column(nullable = false)
    private Integer repeticiones;

    @NotNull(message = "El tiempo de descanso es obligatorio")
    @Column(name = "descanso_segundos", nullable = false)
    private Integer descansoSegundos;

    @NotNull(message = "El orden del ejercicio es obligatorio")
    @Column(nullable = false)
    private Integer orden;

    @Size(max = 255, message = "Las observaciones no pueden superar los 255 caracteres")
    @Column(length = 255)
    private String observaciones;


    //Relacion con ejercicio (FK)
    @ManyToOne
    @JoinColumn(name = "id_ejercicio")
    private Ejercicio ejercicio;
    
    
}