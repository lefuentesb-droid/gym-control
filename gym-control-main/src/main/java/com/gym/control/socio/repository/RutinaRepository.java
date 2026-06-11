package com.gym.control.socio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.control.socio.model.Rutina;

@Repository
public interface RutinaRepository extends JpaRepository<Rutina, Integer>{


    List<Rutina> findByEstado(Boolean estado);

    List<Rutina> findByNombre(String nombre);

    List<Rutina> findBySocioId(Integer idSocio);

    List<Rutina> findByEntrenadorId(Integer idEntrenador);
}
