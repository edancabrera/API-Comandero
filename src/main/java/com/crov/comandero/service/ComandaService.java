package com.crov.comandero.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crov.comandero.dto.CrearComandaDTO;
import com.crov.comandero.dto.CrearComandaDetalleDTO;
import com.crov.comandero.dto.ObtenerComandaDTO;
import com.crov.comandero.dto.ObtenerComandaDetalleDTO;
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
    public Integer crearOActualizarComanda(CrearComandaDTO dto){
        Mesa mesa = mesaRepository.findById(dto.getIdMesa()).orElseThrow(() -> new RuntimeException("Mesa no encontrada"));

        Usuario mesero = usuarioRepository.findById(dto.getIdMesero()).orElseThrow(() -> new RuntimeException("Mesero no encontrado"));

        //Buscar comanda activa
        Comanda comanda = comandaRepository
            .findByMesaIdAndEstatus(mesa.getId(), ComandaEstatus.CURSO)
            .orElse(null);
        
        //Crearla comanda sino existe
        if(comanda == null) {
            mesa.setEstatus(MesaEstatus.OCUPADO);
            mesaRepository.save(mesa);

            //Crear Comanda
            comanda = new Comanda();
            comanda.setMesa(mesa);
            comanda.setMesero(mesero);
            comanda.setFechaCreacion(LocalDateTime.now());
            comanda.setEstatus(ComandaEstatus.CURSO);
            comanda.setActivo(true);

            comanda = comandaRepository.save(comanda);
        }

        

        //Crear detalles
        for(CrearComandaDetalleDTO d : dto.getDetalles()) {
            Producto producto = productoRepository.findById(d.getIdPlatillo()).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            //Verificar si ya existe el platillo en la comanda
            ComandaDetalle detalleExistente = comandaDetalleRepository
                .findByComandaIdAndPlatilloIdProductoAndPersona(
                    comanda.getId(),
                    producto.getIdProducto(),
                    d.getPersona()
                ). orElse(null);
            
            if(detalleExistente != null) {
                detalleExistente.setCantidad(d.getCantidad());
                detalleExistente.setComentarios(d.getComentarios());
                comandaDetalleRepository.save(detalleExistente);
            } else {
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
        }
        return comanda.getId();
    }

    public ObtenerComandaDTO obtenerComandaActivaPorMesa(Integer idMesa) {
        Comanda comanda = comandaRepository
                .findByMesaIdAndEstatus(idMesa, ComandaEstatus.CURSO)
                .orElseThrow(() -> new RuntimeException("La mesa no tiene comanda activa"));
        
        ObtenerComandaDTO dto = new ObtenerComandaDTO();
        dto.setIdMesa(comanda.getMesa().getId());
        dto.setIdMesero(comanda.getMesero().getIdu());

        double total = 0;

        List<ObtenerComandaDetalleDTO> detalles = comanda.getDetalles()
            .stream()
            .map( detalle -> {
                ObtenerComandaDetalleDTO  d = new ObtenerComandaDetalleDTO();
                d.setId(detalle.getId());
                d.setIdComanda(detalle.getComanda().getId());
                d.setIdPlatillo(detalle.getPlatillo().getIdProducto());
                d.setNombre(detalle.getPlatillo().getNombre());
                d.setCantidad(detalle.getCantidad());
                d.setPersona(detalle.getPersona());
                d.setComentarios(detalle.getComentarios());
                d.setIdCategoriaPlatillo(detalle.getPlatillo().getCategoriaPlatillo().getId());
                d.setPrecio(detalle.getPrecio());
                d.setEstatusCocina(detalle.getEstatusCocina());
                return d;
            })
            .toList();

            total = comanda.getDetalles()
                .stream()
                .mapToDouble(d -> d.getCantidad() * d.getPrecio())
                .sum();
            
            dto.setDetalles(detalles);
            dto.setTotal(total);
            
            return dto;
    }

    @Transactional
    public void cancelarComanda(Integer idMesa, Integer idUsuario) {
        Comanda comanda = comandaRepository
            .findByMesaIdAndEstatus(idMesa, ComandaEstatus.CURSO)
            .orElseThrow(() -> new RuntimeException("No hay comanda activa para cancelar"));
        
        Usuario usuario = usuarioRepository.findById(idUsuario)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        comanda.setEstatus(ComandaEstatus.CANCELADO);
        comanda.setUsuarioCancelado(usuario);
        comanda.setFechaCancelacion(LocalDateTime.now());

        comandaRepository.save(comanda);

        Mesa mesa = comanda.getMesa();
        mesa.setEstatus(MesaEstatus.DISPONIBLE);
        mesaRepository.save(mesa);

        //Cambiar el estatus y mesa principal de las mesas unidas, si es que existen
        List<Mesa> mesasUnidas = mesaRepository
            .findByMesaPrincipal_IdAndActivoTrue(mesa.getId());
        for(Mesa m : mesasUnidas) {
            m.setEstatus(MesaEstatus.DISPONIBLE);
            m.setMesaPrincipal(null);
        }
        mesaRepository.saveAll(mesasUnidas);
    }
}