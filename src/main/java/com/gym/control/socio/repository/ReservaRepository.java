package com.gym.control.socio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.control.socio.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> { //basicamente nos da las funciones del CRUD
    List<Reserva> findBySocioId(Integer socioId); //esto busca a las reservas por el id del socio
}
