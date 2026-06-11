package com.gym.control.socio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.control.socio.model.Membresia;

@Repository
public interface MembresiaRepository extends JpaRepository<Membresia,Integer>{


    List<Membresia> findByEstado(String estado);//Buscar por estado 
    List<Membresia> findByTipo(String tipo);//buscar por tipo 
    List<Membresia> findBySocioId(Integer idSocio);//buscar por socio 

    
}
