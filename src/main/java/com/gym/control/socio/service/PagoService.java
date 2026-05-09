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
    
    @Autowired //esto llama al pago repository para que pueda usar sus metodos
    private PagoRepository pagoRepository;

    @Autowired //lo mismo con el de arriba pero con el socio
    private SocioRepository socioRepository;

    //Obtener todos los pagos
    public List<PagoDTO> obtenerTodos() { //busca la lista de pagos y los convierte a DTO para que el usuario entienda mejor la informacion
        return pagoRepository.findAll().stream().map(this::convertirADTO).toList();
    }

    //Registrar pago
    public PagoDTO registrarPago(Pago pago, Integer socioId) { //recibe un pago y el id del socio, busca el socio por su id, si no lo encuentra lanza una excepcion
        Socio socio = socioRepository.findById(socioId)
            .orElseThrow(() -> new RuntimeException("¡Socio no encontrado!"));
        pago.setSocio(socio);
        pago.setEstado(true); // Se crea como activo por defecto
        return convertirADTO(pagoRepository.save(pago));
    }

    //               ACTUALIZAR PAGO
    //public Pago actualizarPago(Integer id, Pago datosNuevos) {
        //Pago existente = pagoRepository.findById(id)
            //.orElseThrow(() -> new RuntimeException("¡Pago no encontrado!"));
        //if (datosNuevos.getMonto() != null) existente.setMonto(datosNuevos.getMonto());
        //if (datosNuevos.getComprobante() != null) existente.setComprobante(datosNuevos.getComprobante());
        //if (datosNuevos.getEstado() != null) existente.setEstado(datosNuevos.getEstado());
        
        //return pagoRepository.save(existente);
   //}

    private PagoDTO convertirADTO(Pago pago) { //convierte un pago a un DTO para que el usuario entienda mejor la informacion
        PagoDTO dto = new PagoDTO();
        dto.setId(pago.getId());
        dto.setMonto(pago.getMonto());
        dto.setFechaPago(pago.getFechaPago());
        dto.setComprobante(pago.getComprobante());
        dto.setEstado(pago.getEstado());
        dto.setSocioId(pago.getSocio().getId());
        //dto.setMembresiaId(pago.getMembresia().getId());
        //dto.setMetodoPagoId(pago.getMetodoPago().getId());
        return dto;
    }
}
