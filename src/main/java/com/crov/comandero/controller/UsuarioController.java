package com.crov.comandero.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.crov.comandero.dto.UsuarioDTO;
import com.crov.comandero.service.UsuarioService;
import com.crov.comandero.service.exception.ClaveInvalidaException;

@RestController
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //GET /login
    @GetMapping("/login/{claveComanda}")
    public ResponseEntity <?> login(@PathVariable String claveComanda) {
        if(claveComanda == null || claveComanda.length() != 6){ throw new ClaveInvalidaException(); }
        
        UsuarioDTO usuario = usuarioService.login(claveComanda);
        return ResponseEntity.ok(usuario);
    }
}