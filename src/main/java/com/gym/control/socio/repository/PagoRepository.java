package com.gym.control.socio.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.control.socio.model.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> { // basicamente nos da las funciones del CRUD
    List<Pago> findBySocioId(Integer socioId); //esto busca a los pagos por el id del socio
    List<Pago> findByEstado(Boolean estado); //esto busca a los pagos por su estado (true o false)
}
