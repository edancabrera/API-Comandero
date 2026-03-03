package com.crov.comandero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crov.comandero.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer>{ 

}