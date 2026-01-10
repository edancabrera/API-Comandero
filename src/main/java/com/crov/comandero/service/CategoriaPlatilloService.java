package com.crov.comandero.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crov.comandero.dto.ProductoPlatilloDTO;
import com.crov.comandero.model.CategoriaPlatillo;
import com.crov.comandero.model.ComplementosCategoriaPlatillo;
import com.crov.comandero.model.ComplementosPlatillo;
import com.crov.comandero.model.Menu;
import com.crov.comandero.repository.CategoriaPlatilloRepository;
import com.crov.comandero.repository.ProductoRepository;

@Service
public class CategoriaPlatilloService {
    private final CategoriaPlatilloRepository categoriaPlatilloRepository;
    private final ProductoRepository productoRepository;

    public CategoriaPlatilloService (CategoriaPlatilloRepository categoriaPlatilloRepository, ProductoRepository productoRepository){
        this.categoriaPlatilloRepository = categoriaPlatilloRepository;
        this.productoRepository = productoRepository;
    }

    public List<Menu> listarMenus(){
        return categoriaPlatilloRepository.findMenusUnicos();
    }

    public List<CategoriaPlatillo> listarCategoriasPorMenu(Menu menu){
        return categoriaPlatilloRepository.findByMenuAndActivoTrue(menu);
    }

    public List<ProductoPlatilloDTO> obtenerPlatillos( Integer idCategoria) {
        return  productoRepository.findByCategoriaPlatillo_IdAndActivoTrueAndPlatilloTrueAndMostrarEnElMenuTrue(idCategoria)
                .stream()
                .map(platillo -> new ProductoPlatilloDTO(
                    platillo.getIdProducto(),
                    platillo.getNombre(),
                    platillo.getPrecio1(),
                    platillo.getCategoriaPlatillo() != null ? platillo.getCategoriaPlatillo().getId() : null
                ))
                .toList();
        
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