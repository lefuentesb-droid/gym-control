package com.gym.control.socio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.control.socio.dto.PagoDTO;
import com.gym.control.socio.model.Pago;
import com.gym.control.socio.model.Socio;
import com.gym.control.socio.repository.PagoRepository;
import com.gym.control.socio.repository.SocioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PagoService {
    
    @Autowired 
    private PagoRepository pagoRepository;

    @Autowired 
    private SocioRepository socioRepository;

    //Obtener todos los pagos
    public List<PagoDTO> obtenerTodos() { 
        return pagoRepository.findAll().stream().map(this::convertirADTO).toList();
    }

    //Registrar pago
    public PagoDTO registrarPago(Pago pago, Integer socioId) { 
        Socio socio = socioRepository.findById(socioId)
            .orElseThrow(() -> new RuntimeException("¡Socio no encontrado!"));
        pago.setSocio(socio);
        pago.setEstado(true); 
        return convertirADTO(pagoRepository.save(pago));
    }

    //               ACTUALIZAR PAGO
    public Pago actualizarPago(Integer id, Pago datosNuevos) {
        Pago existente = pagoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("¡Pago no encontrado!"));
        if (datosNuevos.getMonto() != null) existente.setMonto(datosNuevos.getMonto());
        if (datosNuevos.getComprobante() != null) existente.setComprobante(datosNuevos.getComprobante());
        if (datosNuevos.getEstado() != null) existente.setEstado(datosNuevos.getEstado());
        
        return pagoRepository.save(existente);
   }

    private PagoDTO convertirADTO(Pago pago) { 
        PagoDTO dto = new PagoDTO();
        dto.setId(pago.getId());
        dto.setMonto(pago.getMonto());
        dto.setFechaPago(pago.getFechaPago());
        dto.setComprobante(pago.getComprobante());
        dto.setEstado(pago.getEstado());
        if (pago.getSocio() != null) {
            dto.setSocioId(pago.getSocio().getId());
        }
        
        if (pago.getMembresia() != null) {
            dto.setMembresiaId(pago.getMembresia().getIdmembresia());
        }
        
        if (pago.getMetodoPago() != null) { 
            dto.setMetodoPagoId(pago.getMetodoPago().getIdMetodoPago());
        }
        return dto;
    }

}
