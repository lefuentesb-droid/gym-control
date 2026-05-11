package com.gym.control.socio.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gym.control.socio.model.Clase;

@Repository
public interface ClaseRepository extends JpaRepository<Clase, Integer> {

    List<Clase> findByEstado(Boolean estado);

    List<Clase> findByNombre(String nombre);

    List<Clase> findByEntrenadorId(Integer idEntrenador);
    
}
