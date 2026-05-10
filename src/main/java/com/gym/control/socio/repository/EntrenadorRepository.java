package com.gym.control.socio.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gym.control.socio.model.Entrenador;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Integer> {
    List<Entrenador> findByEstado(Boolean estado);
}