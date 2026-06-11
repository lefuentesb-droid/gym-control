package com.gym.control.socio.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.control.socio.model.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> { 
    List<Pago> findBySocioId(Integer socioId); 
    List<Pago> findByEstado(Boolean estado); 
}
