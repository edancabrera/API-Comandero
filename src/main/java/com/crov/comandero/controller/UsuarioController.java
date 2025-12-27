package com.crov.comandero.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.crov.comandero.dto.UsuarioDTO;
import com.crov.comandero.service.UsuarioService;

@RestController
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //GET /login
    @GetMapping("/login/{claveComanda}")
    public ResponseEntity <UsuarioDTO> login(@PathVariable String claveComanda) {
        UsuarioDTO usuario = usuarioService.login(claveComanda);
        if (usuario == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(usuario);
    }
}