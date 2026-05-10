package com.gym.control.socio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gym.control.socio.model.RutinaEjercicio;

@Repository
public interface RutinaEjercicioRepository extends JpaRepository<RutinaEjercicio, Integer> { 
    //como aqui no esta el estado, no se necesita un metodo para filtrar por estado
}