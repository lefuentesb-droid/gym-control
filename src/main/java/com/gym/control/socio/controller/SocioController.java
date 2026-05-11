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
import com.gym.control.socio.dto.SocioDTO;
import com.gym.control.socio.model.Socio;
import com.gym.control.socio.service.SocioService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/socios")
public class SocioController {

    @Autowired
    private SocioService socioService;

    @GetMapping
    public ResponseEntity<List<SocioDTO>> todosLosUsuario() {
        List<SocioDTO> socios = socioService.obtenerTodos();
        if (socios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(socios, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SocioDTO> buscarPorId(@PathVariable Integer id) {
        try {
            SocioDTO soci = socioService.buscarPorId(id);
            return new ResponseEntity<>(soci, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<SocioDTO> agregarSocio(@Valid @RequestBody Socio soci) {
        try {
            Socio guardado = socioService.guardarSocio(soci);
            SocioDTO dto = socioService.buscarPorId(guardado.getId());

            return new ResponseEntity<>(dto, HttpStatus.CREATED);} 
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
    }

    @PutMapping("/{id}")
    public ResponseEntity<SocioDTO> actualizarSocio(@PathVariable Integer id, @RequestBody Socio soci){
        try{
                Socio newSoci = socioService.actualizarSocio(id, soci);
                SocioDTO dto = socioService.buscarPorId(newSoci.getId());
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarSocio(@PathVariable Integer id) {
        String resultado = socioService.eliminar(id);
        // Si el mensaje contiene "exitosamente", es un éxito
        if (resultado.contains("exitosamente")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }

    
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<SocioDTO>> buscarPorEstado(@PathVariable Boolean estado) {
        List<SocioDTO> socios = socioService.buscarPorEstado(estado);
        if (socios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(socios, HttpStatus.OK);
    }

}
