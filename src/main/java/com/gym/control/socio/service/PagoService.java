package com.gym.control.socio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.control.socio.dto.PagoDTO;
import com.gym.control.socio.model.MetodoPago;
import com.gym.control.socio.model.Pago;
import com.gym.control.socio.model.Socio;
import com.gym.control.socio.repository.MetodoPagoRepository;
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

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    //Obtener todos los pagos
    public List<PagoDTO> obtenerTodos() { //busca la lista de pagos y los convierte a DTO para que el usuario entienda mejor la informacion
        return pagoRepository.findAll().stream().map(this::convertirADTO).toList();
    }

    //Registrar pago
    public PagoDTO registrarPago(PagoDTO dto, Integer socioId) {

        Socio socio = socioRepository.findById(socioId)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado"));

        MetodoPago metodoPago = metodoPagoRepository.findById(dto.getMetodoPagoId())
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));

        Pago pago = new Pago();

        pago.setSocio(socio);
        pago.setMetodoPago(metodoPago);
        pago.setMonto(dto.getMonto());
        pago.setFechaPago(dto.getFechaPago());
        pago.setComprobante(dto.getComprobante());
        pago.setEstado(dto.getEstado());

        Pago guardado = pagoRepository.save(pago);

        return convertirADTO(guardado);
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
        if (pago.getMetodoPago() != null) {
            dto.setMetodoPagoId(pago.getMetodoPago().getIdMetodoPago());
            dto.setNombreMetodoPago(pago.getMetodoPago().getNombre());
        }
        //dto.setMembresiaId(pago.getMembresia().getId());
        //dto.setMetodoPagoId(pago.getMetodoPago().getId());
        return dto;
    }
}
