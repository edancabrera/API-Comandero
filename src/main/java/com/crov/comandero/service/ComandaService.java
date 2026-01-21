package com.crov.comandero.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crov.comandero.dto.CrearComandaDTO;
import com.crov.comandero.dto.CrearComandaDetalleDTO;
import com.crov.comandero.model.Comanda;
import com.crov.comandero.model.ComandaDetalle;
import com.crov.comandero.model.ComandaEstatus;
import com.crov.comandero.model.Mesa;
import com.crov.comandero.model.MesaEstatus;
import com.crov.comandero.model.Producto;
import com.crov.comandero.model.Usuario;
import com.crov.comandero.repository.ComandaDetalleRepository;
import com.crov.comandero.repository.ComandaRepository;
import com.crov.comandero.repository.MesaRepository;
import com.crov.comandero.repository.ProductoRepository;
import com.crov.comandero.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class ComandaService {
    private final ComandaRepository comandaRepository;
    private final ComandaDetalleRepository comandaDetalleRepository;
    private final MesaRepository mesaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    public ComandaService(
        ComandaRepository comandaRepository, 
        ComandaDetalleRepository comandaDetalleRepository,
        MesaRepository mesaRepository, 
        UsuarioRepository usuarioRepository, 
        ProductoRepository productoRepository
    ) {
        this.comandaRepository = comandaRepository;
        this.comandaDetalleRepository = comandaDetalleRepository;
        this.mesaRepository = mesaRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
    }
    
    @Transactional
    public Integer crearComanda(CrearComandaDTO dto){
        Mesa mesa = mesaRepository.findById(dto.getIdMesa()).orElseThrow(() -> new RuntimeException("Mesa no encontrada"));

        Usuario mesero = usuarioRepository.findById(dto.getIdMesero()).orElseThrow(() -> new RuntimeException("Mesero no encontrado"));

        mesa.setEstatus(MesaEstatus.OCUPADO);
        mesaRepository.save(mesa);

        //Crear Comanda
        Comanda comanda = new Comanda();
        comanda.setMesa(mesa);
        comanda.setMesero(mesero);
        comanda.setFechaCreacion(LocalDateTime.now());
        comanda.setEstatus(ComandaEstatus.CURSO);
        comanda.setActivo(true);

        comanda = comandaRepository.save(comanda);

        //Crear detalles
        for(CrearComandaDetalleDTO d : dto.getDetalles()) {
            Producto producto = productoRepository.findById(d.getIdPlatillo()).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            ComandaDetalle detalle = new ComandaDetalle();
            detalle.setComanda(comanda);
            detalle.setPlatillo(producto);
            detalle.setCantidad(d.getCantidad());
            detalle.setPrecio(producto.getPrecio1());
            detalle.setCosto(producto.getCosto());
            detalle.setIva(producto.getImpuesto().getIva());
            detalle.setPersona(d.getPersona());
            detalle.setComentarios(d.getComentarios());
            detalle.setEstatusCocina(1);

            comandaDetalleRepository.save(detalle);
        }
        return comanda.getId();
    }

    public CrearComandaDTO obtenerComanda(Integer idComanda) {
        Comanda comanda = comandaRepository.findById(idComanda).orElseThrow(() -> new RuntimeException("Comanda no encontrada"));

        CrearComandaDTO response = new CrearComandaDTO();
        response.setIdMesa(comanda.getMesa().getId());
        response.setIdMesero(comanda.getMesero().getIdu());

        List<CrearComandaDetalleDTO> detalles = comanda.getDetalles().stream().map(detalle -> {

            Producto producto = detalle.getPlatillo();

            CrearComandaDetalleDTO dto = new CrearComandaDetalleDTO();
            dto.setIdPlatillo(producto.getIdProducto());
            dto.setNombre(producto.getNombre());
            dto.setCantidad(detalle.getCantidad());
            dto.setPersona(detalle.getPersona());
            dto.setComentarios(detalle.getComentarios());
            dto.setIdCategoriaPlatillo(producto.getCategoriaPlatillo().getId());

            return dto;
        })
        .toList();

        response.setDetalles(detalles);
        return response;
    }
}