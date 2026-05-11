package com.gym.control.socio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.control.socio.dto.PagoDTO;

import com.gym.control.socio.service.PagoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    @Autowired //esto llama al pago service para que pueda usar sus metodos
    private PagoService pagoService;

    @GetMapping // Listar/Obtener todos los pagos
    public ResponseEntity<List<PagoDTO>> listarPagos() {
        List<PagoDTO> pagos = pagoService.obtenerTodos();
        if (pagos.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }

    @PostMapping("/socio/{socioId}")
    public ResponseEntity<PagoDTO> crearPago(@Valid @RequestBody PagoDTO dto, @PathVariable Integer socioId) {
        try {
            return new ResponseEntity<>(pagoService.registrarPago(dto, socioId), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
