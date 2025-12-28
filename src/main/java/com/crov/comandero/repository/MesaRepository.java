package com.crov.comandero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crov.comandero.model.Mesa;

public interface MesaRepository extends JpaRepository<Mesa, Integer>{
    List<Mesa> findByIdArea(Integer idArea);
}
