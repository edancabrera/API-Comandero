package com.crov.comandero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crov.comandero.model.Mesa;
import com.crov.comandero.model.MesaEstatus;

import java.util.List;

public interface MesaRepository extends JpaRepository<Mesa, Integer>{
    List<Mesa> findByAreaIdAndActivoTrue(Integer areaid);
    List<Mesa> findByAreaIdAndActivoTrueAndEstatus(Integer areaId, MesaEstatus estatus);
}