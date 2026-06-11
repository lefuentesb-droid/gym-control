package com.gym.control.socio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.control.socio.dto.ReservaDTO;
import com.gym.control.socio.model.Clase;
import com.gym.control.socio.model.Reserva;
import com.gym.control.socio.model.Socio;
import com.gym.control.socio.repository.ClaseRepository;
import com.gym.control.socio.repository.ReservaRepository;
import com.gym.control.socio.repository.SocioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReservaService {

    @Autowired 
    private ReservaRepository reservaRepository;

    @Autowired 
    private SocioRepository socioRepository;

    @Autowired
    private ClaseRepository claseRepository;

    public List<ReservaDTO> obtenerTodas() { 
        return reservaRepository.findAll().stream().map(this::convertirADTO).toList();
    }

    //Buscar por ID
    public ReservaDTO buscarPorId(Integer id) {
        Reserva reserva = reservaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("¡Reserva no encontrada!"));
        return convertirADTO(reserva);
    }

    //Crear reserva
    public ReservaDTO crearReserva(Reserva reserva, Integer socioId, Integer claseId) { 
        Socio socio = socioRepository.findById(socioId)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado")); 
        reserva.setSocio(socio);
        
        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));
        reserva.setClase(clase);

        return convertirADTO(reservaRepository.save(reserva));
    }

    //Actualizar reserva
    public Reserva actualizarReserva(Integer id, Reserva reserva) {
        Reserva res = reservaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("¡Reserva no encontrada!"));
        
        if (reserva.getFechaReserva() != null) {
            res.setFechaReserva(reserva.getFechaReserva());
        }
        if (reserva.getEstado() != null) {
            res.setEstado(reserva.getEstado());
        }
        return reservaRepository.save(res);
    }

    //Eliminar reserva
    public String eliminar(Integer id) {
        try {
            Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! La Reserva con ID " + id + " no existe."));
            reservaRepository.delete(reserva);
            return "La reserva " + reserva.getId() + " ha sido eliminada exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    private ReservaDTO convertirADTO(Reserva reserva) { 
        ReservaDTO dto = new ReservaDTO();
        dto.setId(reserva.getId());
        dto.setFechaReserva(reserva.getFechaReserva());
        dto.setEstado(reserva.getEstado());
        
        if (reserva.getSocio() != null) {
            dto.setSocioId(reserva.getSocio().getId()); 
        }

        if (reserva.getClase() != null) {
            dto.setClaseId(reserva.getClase().getId()); 
        }
        
        return dto;
    }
}
