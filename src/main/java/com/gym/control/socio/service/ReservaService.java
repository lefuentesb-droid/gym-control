package com.gym.control.socio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.control.socio.dto.ReservaDTO;
import com.gym.control.socio.model.Reserva;
import com.gym.control.socio.model.Socio;
import com.gym.control.socio.repository.ReservaRepository;
import com.gym.control.socio.repository.SocioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReservaService {

    @Autowired //esto llama al reserva repository para que pueda usar sus metodos
    private ReservaRepository reservaRepository;

    @Autowired //lo mismo con el de arriba pero con el socio
    private SocioRepository socioRepository;

    public List<ReservaDTO> obtenerTodas() { //busca la lista de reservas y los convierte a DTO para que el usuario entienda mejor la informacion
        return reservaRepository.findAll().stream().map(this::convertirADTO).toList();
    }

    //Buscar por ID
    public ReservaDTO buscarPorId(Integer id) {
        Reserva reserva = reservaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("¡Reserva no encontrada!"));
        return convertirADTO(reserva);
    }

    //Crear reserva
    public ReservaDTO crearReserva(Reserva reserva, Integer socioId) { //recibe una reserva y el id del socio, busca el socio por su id, si no lo encuentra lanza una excepcion
        Socio socio = socioRepository.findById(socioId)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado")); //el orElseThrow valida que el socio exista
        reserva.setSocio(socio);
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

    //Metodo para convertir una reserva a un DTO
    private ReservaDTO convertirADTO(Reserva reserva) { //convierte una reserva a un DTO para que el usuario le sea mas facil entender la informacion
        ReservaDTO dto = new ReservaDTO();
        dto.setId(reserva.getId());
        dto.setFechaReserva(reserva.getFechaReserva());
        dto.setEstado(reserva.getEstado());
        dto.setSocioId(reserva.getSocio().getId());
        return dto;
    }
}
