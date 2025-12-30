package com.crov.comandero.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.crov.comandero.dto.ProductoPrecioDTO;
import com.crov.comandero.model.Producto;
import com.crov.comandero.repository.ProductoRepository;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    public List<ProductoPrecioDTO> listarPrecioProductosActivos(){
        List<Producto> productos = productoRepository.findByActivoAndMostrarEnElMenu(true, true);

        return productos.stream()
                .map(producto -> new ProductoPrecioDTO(
                    producto.getIdProducto(),
                    producto.getNombre(),
                    producto.getPrecio1()
                )).collect(Collectors.toList());
    }
}