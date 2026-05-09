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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservas")

public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Fecha de la reserva
    @Column(nullable = false)
    private LocalDate fechaReserva;

    //Estado de la reserva
    @Column(nullable = false, length = 20)
    private String estado;

    //Relacion con socio (FK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_socio", nullable = false)
    private Socio socio;

    //Relacion con clase (FK)
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "id_clase", nullable = false)
    // private Clase clase;
}
