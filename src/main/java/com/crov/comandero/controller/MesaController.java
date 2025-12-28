package com.crov.comandero.controller;

import org.springframework.web.bind.annotation.RestController;

import com.crov.comandero.model.Mesa;
import com.crov.comandero.service.MesaService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class MesaController {
    private final MesaService mesaService;

    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    //GET /mesa/{idArea}
    @GetMapping("/mesa/{idArea}")
    public ResponseEntity<List<Mesa>> listarMesasPorArea(@PathVariable Integer idArea) {
        return ResponseEntity.ok(mesaService.listarMesasPorArea(idArea));
    }
}
