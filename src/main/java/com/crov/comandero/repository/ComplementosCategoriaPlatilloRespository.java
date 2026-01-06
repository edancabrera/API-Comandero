package com.crov.comandero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crov.comandero.model.ComplementosCategoriaPlatillo;
import java.util.List;


@Repository
public interface ComplementosCategoriaPlatilloRespository extends JpaRepository<ComplementosCategoriaPlatillo, Integer>{
    List<ComplementosCategoriaPlatillo> findByIdCategoriaPlatilloAndActivoTrue(Integer idCategoriaPlatillo);
}
