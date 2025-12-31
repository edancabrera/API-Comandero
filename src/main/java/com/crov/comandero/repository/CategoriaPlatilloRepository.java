package com.crov.comandero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crov.comandero.model.CategoriaPlatillo;
import com.crov.comandero.model.Menu;

public interface CategoriaPlatilloRepository extends JpaRepository<CategoriaPlatillo, Integer>{
    @Query("SELECT DISTINCT c.menu FROM CategoriaPlatillo c WHERE c.activo = true")
    List<Menu> findMenusUnicos();

    List<CategoriaPlatillo> findByMenu(Menu menu);
}
