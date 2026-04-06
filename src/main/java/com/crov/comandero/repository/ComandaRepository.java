package com.crov.comandero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.crov.comandero.model.Comanda;
import com.crov.comandero.model.ComandaEstatus;

import java.util.List;
import java.util.Optional;


public interface ComandaRepository extends JpaRepository<Comanda, Integer>{
    List<Comanda> findByEstatus(ComandaEstatus estatus);

    Optional<Comanda> findFirstByMesaIdAndEstatusOrderByIdDesc(Integer mesaId, ComandaEstatus estatus);

    @Modifying
    @Query("""
        UPDATE Comanda c
        SET c.mesa.id = :idMesaDestino
        WHERE c.id = :idComanda
            """)
    void cambiarMesa(Integer idComanda, Integer idMesaDestino);
}
