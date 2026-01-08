package com.crov.comandero.controller;

import org.springframework.web.bind.annotation.RestController;

import com.crov.comandero.dto.MenuDTO;
import com.crov.comandero.model.CategoriaPlatillo;
import com.crov.comandero.model.Producto;
import com.crov.comandero.service.CategoriaPlatilloService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



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

    //Get /categoria/{mesa}
    @GetMapping("/categoria/{mesa}")
    public ResponseEntity <List<CategoriaPlatillo>> listarCategoriasPorMesa(@PathVariable String mesa) {
        List<CategoriaPlatillo> categorias = categoriaPlatilloService.listarCategoriasPorMesa(mesa);
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/categorias/{id}/productos")
    public ResponseEntity<List<Producto>> listarProductos(@PathVariable Integer id) {
        return ResponseEntity.ok( categoriaPlatilloService.obtenerProductosPorCategoria(id)
    );
}
}