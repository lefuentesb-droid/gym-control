package com.gym.control.socio.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RutinaDTO {


    private Integer id;

    private Integer idSocio;

    private String nombreSocio;

    private Integer idEntrenador;

    private String nombreEntrenador;

    private String nombre;
    
    private String descripcion;

    private LocalDate fechaAsignacion;

    private Boolean estado;
}
