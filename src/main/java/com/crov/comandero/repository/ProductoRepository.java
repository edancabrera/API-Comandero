package com.crov.comandero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crov.comandero.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{

}