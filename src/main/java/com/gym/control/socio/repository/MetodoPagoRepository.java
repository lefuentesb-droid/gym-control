package com.gym.control.socio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.control.socio.model.Membresia;
import com.gym.control.socio.model.MetodoPago;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Integer> {



    List<Membresia> findByEstado(Boolean estado);//Buscar por estado 

    List<MetodoPago> findByNombre(String nombre);


}
