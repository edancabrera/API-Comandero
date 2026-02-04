package com.crov.comandero.controller;

import org.springframework.web.bind.annotation.RestController;

import com.crov.comandero.dto.CrearComandaDTO;
import com.crov.comandero.dto.ObtenerComandaDTO;
import com.crov.comandero.service.ComandaService;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class ComandaController {
    private final ComandaService comandaService;

    public ComandaController(ComandaService comandaService) {
        this.comandaService = comandaService;
    }

    @PostMapping("/comanda")
    public ResponseEntity<Integer> crearOActualizarComanda(@RequestBody CrearComandaDTO dto) {
        Integer idComanda = comandaService.crearOActualizarComanda(dto);
        return ResponseEntity.ok(idComanda);
    }

    @GetMapping("/comanda/mesa/{idMesa}")
    public ResponseEntity<ObtenerComandaDTO> obtenerComandaPorMesa(@PathVariable Integer idMesa) {
        return ResponseEntity.ok(comandaService.obtenerComandaActivaPorMesa(idMesa));
    }

    @PostMapping("/comanda/mesa/{idMesa}/cancelar/{idUsuario}")
    public ResponseEntity<Void> postMethodName(@PathVariable Integer idMesa, @PathVariable Integer idUsuario) {
        comandaService.cancelarComanda(idMesa, idUsuario);
        return ResponseEntity.ok().build();
    }
    
}
