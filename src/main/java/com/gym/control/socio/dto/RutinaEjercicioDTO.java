package com.gym.control.socio.dto;

import lombok.Data;

@Data
public class RutinaEjercicioDTO {
    
    private Integer Id;
    private Integer series;
    private Integer repeticiones;
    private Integer descansoSegundos;
    private Integer orden;
    private String observaciones;

    //Relación con rutina (FK)
    private Integer rutinaCabeceraId;
    //Relacion con ejercicio (FK)
    private Integer ejercicioId;
}