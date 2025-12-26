package com.crov.comandero.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crov.comandero.dto.UsuarioDTO;
import com.crov.comandero.model.TipoCargo;
import com.crov.comandero.model.Usuario;
import com.crov.comandero.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDTO login(String claveComanda) {
        Optional <Usuario> usuarioOpt = usuarioRepository.findByClaveComanda(claveComanda);
        if(usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if(Boolean.TRUE.equals(usuario.getActivo()) && (usuario.getTipoCargo() == TipoCargo.MESERO || usuario.getTipoCargo() == TipoCargo.ADMINISTRADOR)) {
                return new UsuarioDTO(
                    usuario.getIdu(),
                    usuario.getNombre(),
                    usuario.getApellidos(),
                    usuario.getEmail(),
                    usuario.getTipoCargo()
                );
            }
        }
        return null;
    }
}