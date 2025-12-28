package com.crov.comandero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crov.comandero.model.Area;
import java.util.List;


public interface AreaRepository extends JpaRepository<Area, Integer>{
    List<Area> findByActivo(Boolean activo);
}