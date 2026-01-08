package com.crov.comandero.controller;

import org.springframework.web.bind.annotation.RestController;

import com.crov.comandero.dto.MenuDTO;
import com.crov.comandero.model.CategoriaPlatillo;
import com.crov.comandero.model.ComplementosPlatillo;
import com.crov.comandero.model.Menu;
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
    @GetMapping("/menus/{menu}/categorias")
    public ResponseEntity <List<CategoriaPlatillo>> listarCategoriasPorMesa(@PathVariable Menu menu) {
        List<CategoriaPlatillo> categorias = categoriaPlatilloService.listarCategoriasPorMenu(menu);
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/categorias/{id}/productos")
    public ResponseEntity<List<Producto>> listarProductos(@PathVariable Integer id) {
        return ResponseEntity.ok( categoriaPlatilloService.obtenerProductosPorCategoria(id));
    }

    @GetMapping("/categorias/{id}/complementos")
    public ResponseEntity<List<ComplementosPlatillo>> listarComplementos(
            @PathVariable Integer id) {

        List<ComplementosPlatillo> complementos =
            categoriaPlatilloService.obtenerComplementosPorCategoria(id);

        return ResponseEntity.ok(complementos);
    }
}