package com.crov.comandero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crov.comandero.model.Mesa;

import java.util.List;

public interface MesaRepository extends JpaRepository<Mesa, Integer>{
    List<Mesa> findByAreaIdAndActivoTrue(Integer areaid);
}