package com.crov.comandero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crov.comandero.model.Producto;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{

    List<Producto> findByActivo(Boolean activo);
}