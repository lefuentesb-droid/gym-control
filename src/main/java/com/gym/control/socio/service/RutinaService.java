package com.gym.control.socio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.control.socio.dto.RutinaDTO;
import com.gym.control.socio.model.Entrenador;
import com.gym.control.socio.model.Rutina;
import com.gym.control.socio.model.Socio;
import com.gym.control.socio.repository.EntrenadorRepository;
import com.gym.control.socio.repository.RutinaRepository;
import com.gym.control.socio.repository.SocioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RutinaService {

    @Autowired
    private RutinaRepository rutinaRepository;

    @Autowired
    private SocioRepository socioRepository;

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    public List<RutinaDTO> obtenerTodas() {
        return rutinaRepository.findAll()
                .stream()
                .map(this::convertirDTO)
                .toList();
    }

    public RutinaDTO buscarPorId(Integer id) {
        Rutina rutina = rutinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rutina no encontrada"));

        return convertirDTO(rutina);
    }

    public RutinaDTO guardar(RutinaDTO dto) {
        Socio socio = socioRepository.findById(dto.getIdSocio())
                .orElseThrow(() -> new RuntimeException("Socio no encontrado"));

        Entrenador entrenador = entrenadorRepository.findById(dto.getIdEntrenador())
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado"));

        Rutina rutina = new Rutina();

        rutina.setSocio(socio);
        rutina.setEntrenador(entrenador);
        rutina.setNombre(dto.getNombre());
        rutina.setDescripcion(dto.getDescripcion());
        rutina.setFechaAsignacion(dto.getFechaAsignacion());
        rutina.setEstado(dto.getEstado());

        Rutina guardada = rutinaRepository.save(rutina);

        return convertirDTO(guardada);
    }

    public RutinaDTO actualizar(Integer id, RutinaDTO dto) {
        Rutina rutina = rutinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rutina no encontrada"));

        if (dto.getNombre() != null) {
            rutina.setNombre(dto.getNombre());
        }

        if (dto.getDescripcion() != null) {
            rutina.setDescripcion(dto.getDescripcion());
        }

        if (dto.getFechaAsignacion() != null) {
            rutina.setFechaAsignacion(dto.getFechaAsignacion());
        }

        if (dto.getEstado() != null) {
            rutina.setEstado(dto.getEstado());
        }

        if (dto.getIdSocio() != null) {
            Socio socio = socioRepository.findById(dto.getIdSocio())
                    .orElseThrow(() -> new RuntimeException("Socio no encontrado"));

            rutina.setSocio(socio);
        }

        if (dto.getIdEntrenador() != null) {
            Entrenador entrenador = entrenadorRepository.findById(dto.getIdEntrenador())
                    .orElseThrow(() -> new RuntimeException("Entrenador no encontrado"));

            rutina.setEntrenador(entrenador);
        }

        Rutina actualizada = rutinaRepository.save(rutina);

        return convertirDTO(actualizada);
    }

    public String eliminar(Integer id) {
        Rutina rutina = rutinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rutina no encontrada"));

        rutina.setEstado(false);
        rutinaRepository.save(rutina);

        return "Rutina deshabilitada correctamente";
    }

    public List<RutinaDTO> buscarPorEstado(Boolean estado) {
        return rutinaRepository.findByEstado(estado)
                .stream()
                .map(this::convertirDTO)
                .toList();
    }

    public List<RutinaDTO> buscarPorNombre(String nombre) {
        return rutinaRepository.findByNombre(nombre)
                .stream()
                .map(this::convertirDTO)
                .toList();
    }

    public List<RutinaDTO> buscarPorSocio(Integer idSocio) {
        return rutinaRepository.findBySocioId(idSocio)
                .stream()
                .map(this::convertirDTO)
                .toList();
    }

    public List<RutinaDTO> buscarPorEntrenador(Integer idEntrenador) {
        return rutinaRepository.findByEntrenadorId(idEntrenador)
                .stream()
                .map(this::convertirDTO)
                .toList();
    }

    private RutinaDTO convertirDTO(Rutina rutina) {
        RutinaDTO dto = new RutinaDTO();

        dto.setId(rutina.getId());
        dto.setNombre(rutina.getNombre());
        dto.setDescripcion(rutina.getDescripcion());
        dto.setFechaAsignacion(rutina.getFechaAsignacion());
        dto.setEstado(rutina.getEstado());

        if (rutina.getSocio() != null) {
            dto.setIdSocio(rutina.getSocio().getId());
            dto.setNombreSocio(rutina.getSocio().getNombre());
        }

        if (rutina.getEntrenador() != null) {
            dto.setIdEntrenador(rutina.getEntrenador().getId());
            dto.setNombreEntrenador(rutina.getEntrenador().getNombre());
        }

        return dto;
    }    
}
