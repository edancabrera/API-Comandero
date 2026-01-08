package com.crov.comandero.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crov.comandero.dto.ProductoPrecioDTO;
import com.crov.comandero.service.ProductoService;



@RestController
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService){
        this.productoService = productoService;
    }

    @GetMapping("/precios")
    public ResponseEntity<List<ProductoPrecioDTO>> listarPrecioProductos() {
        return ResponseEntity.ok(productoService.listarPrecioProductosActivos());
    }
}
