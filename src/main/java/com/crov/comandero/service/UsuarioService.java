package com.crov.comandero.service;

import org.springframework.stereotype.Service;

import com.crov.comandero.dto.UsuarioDTO;
import com.crov.comandero.model.TipoCargo;
import com.crov.comandero.model.Usuario;
import com.crov.comandero.repository.UsuarioRepository;
import com.crov.comandero.service.exception.RolNoPermitidoException;
import com.crov.comandero.service.exception.UsuarioInactivoException;
import com.crov.comandero.service.exception.UsuarioNoEncontradoException;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDTO login(String claveComanda) {
        Usuario usuario = usuarioRepository.findByClaveComanda(claveComanda)
                .orElseThrow(UsuarioNoEncontradoException::new);

        if (!Boolean.TRUE.equals(usuario.getActivo())) {
            throw new UsuarioInactivoException();
        }

        if (usuario.getTipoCargo() != TipoCargo.MESERO || usuario.getTipoCargo() != TipoCargo.ADMINISTRADOR) {
            throw new RolNoPermitidoException();
        }
        return new UsuarioDTO(
                usuario.getIdu(),
                usuario.getNombre(),
                usuario.getApellidos(),
                usuario.getEmail(),
                usuario.getTipoCargo());
    }
}