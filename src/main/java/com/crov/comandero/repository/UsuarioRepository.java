package com.crov.comandero.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crov.comandero.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByClaveComanda(String claveComanda);
}
