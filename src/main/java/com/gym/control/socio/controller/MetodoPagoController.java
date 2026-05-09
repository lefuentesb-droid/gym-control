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

import com.gym.control.socio.dto.MetodoPagoDTO;
import com.gym.control.socio.model.MetodoPago;
import com.gym.control.socio.service.MetodoPagoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/metodos-pago")
public class MetodoPagoController {
    @Autowired
    private MetodoPagoService metodoPagoService;

    @GetMapping
    public ResponseEntity<List<MetodoPagoDTO>> listarTodos() {
        List<MetodoPagoDTO> metodos = metodoPagoService.obtenerTodos();

        if (metodos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(metodos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPagoDTO> buscarPorId(@PathVariable Integer id) {
        try {
            MetodoPagoDTO dto = metodoPagoService.buscarPorId(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<MetodoPagoDTO> crearMetodoPago(@Valid @RequestBody MetodoPago metodoPago) {
        try {
            MetodoPagoDTO nuevo = metodoPagoService.guardar(metodoPago);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoPagoDTO> actualizarMetodoPago(@PathVariable Integer id,
                                                              @RequestBody MetodoPago metodoPago) {
        try {
            MetodoPago actualizado = metodoPagoService.actualizar(id, metodoPago);
            MetodoPagoDTO dto = metodoPagoService.buscarPorId(actualizado.getIdMetodoPago());
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarMetodoPago(@PathVariable Integer id) {
        try {
            String resultado = metodoPagoService.eliminar(id);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<MetodoPagoDTO>> buscarPorNombre(@PathVariable String nombre) {
        List<MetodoPagoDTO> metodos = metodoPagoService.buscarPorNombre(nombre);

        if (metodos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(metodos, HttpStatus.OK);
    }
}
