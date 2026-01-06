package com.crov.comandero.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.crov.comandero.service.ComplementosCategoriaPlatilloService;

@RestController
public class ComplementosCategoriaPlatilloController {

 
    private final ComplementosCategoriaPlatilloService complementosCategoriaPlatilloService;

        public ComplementosCategoriaPlatilloController(ComplementosCategoriaPlatilloService complementosCategoriaPlatilloService){
            this.complementosCategoriaPlatilloService = complementosCategoriaPlatilloService;
        }
        
        
    

    @GetMapping("/complementos/{idCategoria}")
    public ResponseEntity<List<String>> obtenerComplementos(@PathVariable Integer idCategoria){
        return ResponseEntity.ok(
            complementosCategoriaPlatilloService.obtenerComplementos(idCategoria)
        );
    }

}
