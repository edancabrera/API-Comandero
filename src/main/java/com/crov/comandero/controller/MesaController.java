package com.crov.comandero.controller;

import org.springframework.web.bind.annotation.RestController;

import com.crov.comandero.dto.DescripcionMesaDTO;
import com.crov.comandero.dto.MesaDTO;
import com.crov.comandero.dto.MesasIdsDTO;
import com.crov.comandero.model.MesaEstatus;
import com.crov.comandero.service.MesaService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class MesaController {
    private final MesaService mesaService;

    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @GetMapping("/areas/{id}/mesas")
    public ResponseEntity<List<MesaDTO>> listarMesasPorArea(@PathVariable Integer id, @RequestParam(required = false) MesaEstatus estatus) {
        if (estatus == null){
            return ResponseEntity.ok(mesaService.obtenerMesasPorArea(id));
        }
        return ResponseEntity.ok(mesaService.listarMesasPorEstatus(id, estatus));
    }

    @PutMapping("mesas/{id}/descripcion")
    public ResponseEntity<Void> agregarDescripcionAMesa(@PathVariable Integer id, @RequestBody DescripcionMesaDTO dto) {
        mesaService.agregarDescripcionAMesa(id, dto.getDescripcion());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/mesas/principal/{id}")
    public ResponseEntity<List<MesaDTO>> listarMesasPorMesaPrincipal(@PathVariable Integer id) {
        return ResponseEntity.ok(mesaService.obtenerMesasPorMesaPrincipal(id));
    }

    @PutMapping("/mesas/remover-mesa-principal")
    public ResponseEntity<Void> removerMesaPrincipal(@RequestBody MesasIdsDTO dto) {
        mesaService.removerMesaPrincipal(dto.getIds());
        return ResponseEntity.noContent().build();
    }
    
}
