package com.crov.comandero.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crov.comandero.dto.ProductoPlatilloDTO;
import com.crov.comandero.model.CategoriaPlatillo;
import com.crov.comandero.model.ComplementosPlatillo;
import com.crov.comandero.model.Menu;
import com.crov.comandero.repository.CategoriaPlatilloRepository;
import com.crov.comandero.repository.ComplementosCategoriaPlatilloRepository;
import com.crov.comandero.repository.ProductoRepository;

@Service
public class CategoriaPlatilloService {
    private final CategoriaPlatilloRepository categoriaPlatilloRepository;
    private final ProductoRepository productoRepository;
    private final ComplementosCategoriaPlatilloRepository complementosCategoriaPlatilloRepository;

    public CategoriaPlatilloService (CategoriaPlatilloRepository categoriaPlatilloRepository, ProductoRepository productoRepository, ComplementosCategoriaPlatilloRepository complementosCategoriaPlatilloRepository){
        this.categoriaPlatilloRepository = categoriaPlatilloRepository;
        this.productoRepository = productoRepository;
        this.complementosCategoriaPlatilloRepository = complementosCategoriaPlatilloRepository;
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

    public List<ComplementosPlatillo> obtenerComplementos(Integer idCategoria){
        return complementosCategoriaPlatilloRepository.findComplementosActivosPorCategoria(idCategoria);
    }
}