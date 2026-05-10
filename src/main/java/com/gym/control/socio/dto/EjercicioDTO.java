package com.gym.control.socio.dto;

import lombok.Data;

@Data
public class EjercicioDTO {
    
    private Integer Id;
    private String nombre;
    private String descripcion;
    private String grupoMuscular;
    private String dificultad;
    private String videoUrl;
    private Boolean estado;
}