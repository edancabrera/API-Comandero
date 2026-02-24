package com.crov.comandero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crov.comandero.model.Parametros;

@Repository
public interface ParametrosRepository extends JpaRepository<Parametros, Integer>{

}
