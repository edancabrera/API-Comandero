package com.crov.comandero.controller;

import org.springframework.web.bind.annotation.RestController;

import com.crov.comandero.dto.DescripcionMesaDTO;
import com.crov.comandero.dto.MesaDTO;
import com.crov.comandero.service.MesaService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class MesaController {
    private final MesaService mesaService;

    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @GetMapping("/areas/{id}/mesas")
    public ResponseEntity<List<MesaDTO>> listarMesasPorArea(@PathVariable Integer id) {
        return ResponseEntity.ok(mesaService.obtenerMesasPorArea(id));
    }

    @PutMapping("mesas/{id}/descripcion")
    public ResponseEntity<Void> agregarDescripcionAMesa(@PathVariable Integer id, @RequestBody DescripcionMesaDTO dto) {
        mesaService.agregarDescripcionAMesa(id, dto.getDescripcion());

        return ResponseEntity.noContent().build();
    }
}
