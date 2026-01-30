package com.crov.comandero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.crov.comandero.model.Mesa;
import com.crov.comandero.model.MesaEstatus;

import java.util.List;

public interface MesaRepository extends JpaRepository<Mesa, Integer>{
    List<Mesa> findByAreaIdAndActivoTrue(Integer areaid);
    List<Mesa> findByAreaIdAndActivoTrueAndEstatus(Integer areaId, MesaEstatus estatus);
    List<Mesa> findByMesaPrincipal_IdAndActivoTrue(Integer mesaPrincipalId);

    @Modifying(clearAutomatically = true)
    @Query("""
            UPDATE Mesa m
            SET m.mesaPrincipal = null,
                m.estatus = :estatus
            WHERE m.id IN :ids
            """)
    int removerMesaPrincipal(List<Integer> ids, MesaEstatus estatus);

    @Modifying(clearAutomatically = true)
    @Query("""
            UPDATE Mesa m
            SET m.mesaPrincipal = :mesaPrincipal,
                m.estatus = :estatus
            WHERE m.id IN :ids
            """)
    int agregarMesaPrincipal(Mesa mesaPrincipal, List<Integer> ids, MesaEstatus estatus);
}