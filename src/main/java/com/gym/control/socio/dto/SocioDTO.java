package com.gym.control.socio.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class SocioDTO {

    private Integer id_socio;
    
    private String rut;

    
    private String nombre;

   
    private String apellido;

   
    private String correo;

    private String telefono;

    
    private String direccion;

    private LocalDate fechaNacimiento;//no usamos notblank porque es solo para string 

    
    private Boolean estado;
    
}

