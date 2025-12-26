package com.crov.comandero.controller;

import org.springframework.web.bind.annotation.RestController;
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
    public UsuarioDTO login(@PathVariable String claveComanda) {
        return usuarioService.login(claveComanda);
    }
}