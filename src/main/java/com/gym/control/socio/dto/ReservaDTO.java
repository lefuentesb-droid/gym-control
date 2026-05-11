package com.gym.control.socio.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReservaDTO {

    private Integer id;

    private LocalDate fechaReserva;

    private String estado;
    
    //Relacion con socio (FK)
    private Integer socioId;

    //Relacion con clase (FK)
    private String nombreSocio;

    
    
}
