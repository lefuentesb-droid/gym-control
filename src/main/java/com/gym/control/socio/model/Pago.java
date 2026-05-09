package com.gym.control.socio.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pagos")
public class Pago {

    //PK de pago
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Monto del pago
    @Column(nullable = false)
    private Double monto;

    //Fecha del pago
    @Column(nullable = false)
    private LocalDate fechaPago;

    //Metodo de pago 
    
    @ManyToOne
    @JoinColumn(name = "id_metodo_pago", nullable = false)
    private MetodoPago metodoPago;

    //Comprobante del pago
    @NotBlank(message = "El número de comprobante es obligatorio")
    @Size(min = 5, max = 50, message = "El comprobante debe tener entre 5 y 50 caracteres")
    @Column(nullable = false, length = 50)
    private String comprobante;

    //Estado del pago 
    @NotNull(message = "El estado es obligatorio")
    @Column(nullable = false)
    private Boolean estado;

    //Relacion con socio (FK)
    @ManyToOne(fetch = FetchType.LAZY)  //ManyToOne por que muchos pagos pueden pertenecer a un socio ????????????????????????????????????????????????????????????????????????
    //???????????????????????????????????
    @JoinColumn(name = "socio_id", nullable = false)
    private Socio socio;

    //Relacion con membresia (FK)
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "membresia_id", nullable = false)
    //private Membresia membresia;

    //Relacion con metodo_pago (FK)
    //@ManyToOne(fetch = FetchType.LAZY)
    //@joinColumn(name = "id_metodo_pago", nullable = false)
    //private MetodoPago metodoPago;
}
