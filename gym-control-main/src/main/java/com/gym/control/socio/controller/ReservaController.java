package com.gym.control.socio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.control.socio.dto.ReservaDTO;
import com.gym.control.socio.model.Reserva;
import com.gym.control.socio.service.ReservaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping 
    public ResponseEntity<List<ReservaDTO>> todasLasReservas() { 
        List<ReservaDTO> reservas = reservaService.obtenerTodas();
        if (reservas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    @PostMapping("/socio/{socioId}") 
    public ResponseEntity<ReservaDTO> crearReserva(@Valid @RequestBody Reserva reserva, @PathVariable Integer socioId, @PathVariable Integer claseId) {
        try {
            return new ResponseEntity<>(reservaService.crearReserva(reserva, socioId, claseId), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}") 
    public ResponseEntity<ReservaDTO> actualizarReserva(@PathVariable Integer id, @RequestBody Reserva reserva) {
        try {
            Reserva newRes = reservaService.actualizarReserva(id, reserva);
            ReservaDTO dto = reservaService.buscarPorId(newRes.getId());
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}") 
    public ResponseEntity<String> eliminarReserva(@PathVariable Integer id) {
        String resultado = reservaService.eliminar(id);
        if (resultado.contains("exitosamente")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }
}
