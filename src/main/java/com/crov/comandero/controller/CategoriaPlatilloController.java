package com.crov.comandero.controller;

import org.springframework.web.bind.annotation.RestController;

import com.crov.comandero.dto.MenuDTO;
import com.crov.comandero.service.CategoriaPlatilloService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class CategoriaPlatilloController {
    private final CategoriaPlatilloService categoriaPlatilloService;

    public CategoriaPlatilloController(CategoriaPlatilloService categoriaPlatilloService){
        this.categoriaPlatilloService = categoriaPlatilloService;
    }

    //GET /menus
    @GetMapping("/menus")
    public ResponseEntity<List<MenuDTO>> listarMenus() {
        return ResponseEntity.ok(categoriaPlatilloService.listarMenusExistentes());
    }
    
}
