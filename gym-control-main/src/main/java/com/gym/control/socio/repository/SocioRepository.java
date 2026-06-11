package com.gym.control.socio.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gym.control.socio.model.Socio;

@Repository
public interface SocioRepository extends JpaRepository<Socio,Integer>{

    List<Socio> findByEstado(Boolean estado);//Buscar los socios por estado
}
