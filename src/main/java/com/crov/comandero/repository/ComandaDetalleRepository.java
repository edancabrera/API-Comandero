package com.crov.comandero.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crov.comandero.model.ComandaDetalle;

public interface ComandaDetalleRepository extends JpaRepository<ComandaDetalle, Integer>{
    Optional<ComandaDetalle> findByComandaIdAndPlatilloIdProductoAndPersona(
        Integer idComanda,
        Integer idProducto,
        Integer persona
    );
}