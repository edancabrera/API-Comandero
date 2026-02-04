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
    public ResponseEntity<Integer> crearComanda(@RequestBody CrearComandaDTO dto) {
        Integer idComanda = comandaService.crearComanda(dto);
        return ResponseEntity.ok(idComanda);
    }

    @GetMapping("/comanda/mesa/{idMesa}")
    public ResponseEntity<ObtenerComandaDTO> obtenerComandaPorMesa(@PathVariable Integer idMesa) {
        return ResponseEntity.ok(comandaService.obtenerComandaActivaPorMesa(idMesa));
    }
    
}
