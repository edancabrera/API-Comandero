package com.crov.comandero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crov.comandero.model.ComplementosCategoriaPlatillo;
import com.crov.comandero.model.ComplementosPlatillo;

public interface ComplementosCategoriaPlatilloRepository extends JpaRepository<ComplementosCategoriaPlatillo, Integer>{
    @Query("""
        SELECT ccp.complementosPlatillo
        FROM ComplementosCategoriaPlatillo ccp
        WHERE ccp.categoriaPlatillo.id = :idCategoria
          AND ccp.activo = true
          AND ccp.complementosPlatillo.activo = true
    """)
    List<ComplementosPlatillo> findComplementosActivosPorCategoria(Integer idCategoria);
}