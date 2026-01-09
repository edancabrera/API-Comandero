package com.crov.comandero.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crov.comandero.model.CategoriaPlatillo;
import com.crov.comandero.model.ComplementosCategoriaPlatillo;
import com.crov.comandero.model.ComplementosPlatillo;
import com.crov.comandero.model.Menu;
import com.crov.comandero.model.Producto;
import com.crov.comandero.repository.CategoriaPlatilloRepository;

@Service
public class CategoriaPlatilloService {
    private final CategoriaPlatilloRepository categoriaPlatilloRepository;

    public CategoriaPlatilloService (CategoriaPlatilloRepository categoriaPlatilloRepository){
        this.categoriaPlatilloRepository = categoriaPlatilloRepository;
    }

    public List<Menu> listarMenus(){
        return categoriaPlatilloRepository.findMenusUnicos();
    }

    public List<CategoriaPlatillo> listarCategoriasPorMenu(Menu menu){
        return categoriaPlatilloRepository.findByMenuAndActivo(menu, true);
    }

    public List<Producto> obtenerProductosPorCategoria(Integer idCategoria) {
        CategoriaPlatillo categoriaPlatillo = categoriaPlatilloRepository.findById(idCategoria).orElseThrow(()-> new RuntimeException("Categoria no encontrada"));

        return categoriaPlatillo.getProductos();
    }

    public List<ComplementosPlatillo> obtenerComplementosPorCategoria(Integer idCategoria) {

        CategoriaPlatillo categoria = categoriaPlatilloRepository.findById(idCategoria)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        return categoria.getComplementos()
            .stream()
            .filter(rel -> Boolean.TRUE.equals(rel.getActivo()))
            .map(ComplementosCategoriaPlatillo::getComplementosPlatillo)
            .filter(comp -> Boolean.TRUE.equals(comp.getActivo()))
            .toList();
    }
}