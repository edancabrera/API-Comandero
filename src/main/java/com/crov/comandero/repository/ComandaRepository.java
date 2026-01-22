package com.crov.comandero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crov.comandero.model.Comanda;
import com.crov.comandero.model.ComandaEstatus;

import java.util.List;


public interface ComandaRepository extends JpaRepository<Comanda, Integer>{
    List<Comanda> findByEstatus(ComandaEstatus estatus);
}
