package com.gym.control.socio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ejercicios")
public class Ejercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @NotBlank(message = "El nombre del ejercicio es obligatorio")
    @Size(min = 3, max = 100)
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 5, max = 255)
    @Column(nullable = false, length = 255)
    private String descripcion;

    @NotBlank(message = "El grupo muscular es obligatorio")
    @Column(name = "grupo_muscular", nullable = false, length = 50)
    private String grupoMuscular;

    @NotBlank(message = "La dificultad es obligatoria")
    @Column(nullable = false, length = 20)
    private String dificultad; 

    @Size(max = 255, message = "La URL del video no puede superar los 255 caracteres")
    @Column(name = "video_url", length = 255)
    private String videoUrl; 

    @NotNull(message = "El estado es obligatorio")
    @Column(nullable = false)
    private Boolean estado;
}