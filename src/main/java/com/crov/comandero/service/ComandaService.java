package com.crov.comandero.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.crov.comandero.dto.CrearComandaDTO;
import com.crov.comandero.dto.CrearComandaDetalleDTO;
import com.crov.comandero.model.Comanda;
import com.crov.comandero.model.ComandaDetalle;
import com.crov.comandero.model.ComandaEstatus;
import com.crov.comandero.model.Mesa;
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
            //detalle.setIva(producto.getImpuesto()); falta mapear la columna impuesto en producto y agregar el enum impuesto
            detalle.setPersona(d.getPersona());
            detalle.setComentario(d.getComentario());
            detalle.setEstatusCocina(1);

            comandaDetalleRepository.save(detalle);
        }
        return comanda.getId();
    }
}