package com.gym.control.socio.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    @ManyToOne //ManyToOne porque un socio puede tener muchas reservas
    @JoinColumn(name = "id_socio")
    private Socio socio;

    // Relacion con clase (FK)
    @ManyToOne //ManyToOne porque una clase puede tener muchas reservas
    @JoinColumn(name = "id_clase")
    private Clase clase;
}
