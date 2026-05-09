package com.gym.control.socio.dto;

import lombok.Data;

@Data
public class MetodoPagoDTO {


    private Integer idMetodoPago;

    private String nombre;

    private String descripcion;

    private Boolean estado;
}
