package com.crov.comandero.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.crov.comandero.model.Area;
import com.crov.comandero.service.AreaService;

@RestController
public class AreaController {
    private final AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @GetMapping("/areas")
    public ResponseEntity<List<Area>> listarAreas() {
        return ResponseEntity.ok(areaService.listarAreas());
    }
}