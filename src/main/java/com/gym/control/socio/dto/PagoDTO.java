package com.gym.control.socio.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PagoDTO {
    private Integer id;

    @NotNull(message = "El monto es obligatorio")
    private Double monto;

    private LocalDate fechaPago;

    @NotBlank(message = "El comprobante es obligatorio")
    private String comprobante;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;

    //Relacion con socio (FK)
    private Integer socioId;
    
    private Integer metodoPagoId;
    
    private String nombreMetodoPago;
    //Relacion con membresia (FK)
    //private String/Integer tipoMembresia/membresiaId;

    //Relacion con metodo_pago (FK)
    //private String metodoPago;
}
