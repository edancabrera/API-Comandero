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
    public ResponseEntity <?> login(@PathVariable String claveComanda) {
        if(claveComanda == null || claveComanda.length() != 6){
            return ResponseEntity.badRequest().body("La clave debe tener exactamente 6 caracteres");
        }
        UsuarioDTO usuario = usuarioService.login(claveComanda);

        if (usuario == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Clave inválida");
        }
        return ResponseEntity.ok(usuario);
    }
}