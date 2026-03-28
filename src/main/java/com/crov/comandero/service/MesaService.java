package com.crov.comandero.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.crov.comandero.dto.MesaDTO;
import com.crov.comandero.model.Comanda;
import com.crov.comandero.model.ComandaEstatus;
import com.crov.comandero.model.Mesa;
import com.crov.comandero.model.MesaEstatus;
import com.crov.comandero.repository.ComandaRepository;
import com.crov.comandero.repository.MesaRepository;

import jakarta.transaction.Transactional;

@Service
public class MesaService {
    private final MesaRepository mesaRepository;
    private final ComandaRepository comandaRepository;

    public MesaService(MesaRepository mesaRepository, ComandaRepository comandaRepository){
        this.mesaRepository = mesaRepository;
        this.comandaRepository = comandaRepository;
    }

    public List<MesaDTO> obtenerMesasPorArea(Integer idArea){
        List<Mesa> mesas = mesaRepository.findByAreaIdAndActivoTrue(idArea);

        if(mesas.isEmpty()){ return List.of();}

        List<Integer> mesasIds = mesas.stream()
            .map(Mesa::getId)
            .toList();
        
        List<Object[]> relaciones = mesaRepository.findHijasByMesaPrincipalIds(mesasIds);

        Map<Integer, List<Integer>> mapaHijas = relaciones.stream()
            .collect(Collectors.groupingBy(
                r -> (Integer) r[0],
                Collectors.mapping(r -> (Integer) r[1], Collectors.toList())
            ));

        return mesas.stream()
                .map(mesa -> {
                    MesaDTO m = new MesaDTO();
                    m.setId(mesa.getId());
                    m.setAreaId(mesa.getArea() != null ? mesa.getArea().getId() : null);
                    m.setNombre(mesa.getNombre());
                    m.setEstatus(mesa.getEstatus());
                    m.setMesaPrincipalId(mesa.getMesaPrincipal() != null ? mesa.getMesaPrincipal().getId() : null);
                    m.setMesasHijasIds(mapaHijas.getOrDefault(mesa.getId(), List.of()));

                    return m;
                })
                .toList();
    }

    public void agregarDescripcionAMesa(Integer idMesa, String descripcion){
        Mesa mesa = mesaRepository.findById(idMesa).orElseThrow(() -> new RuntimeException("Mesa no encontrada"));
        mesa.setNombre(descripcion);
        mesaRepository.save(mesa);
    }


    public List<MesaDTO> listarMesasPorEstatus(Integer idArea, MesaEstatus estatus){
        return mesaRepository.findByAreaIdAndActivoTrueAndEstatus(idArea, estatus)
        .stream()
        .map(mesa -> new MesaDTO(
            mesa.getId(),
            mesa.getArea() != null ? mesa.getArea().getId() : null,
            mesa.getNombre(),
            mesa.getEstatus(),
            mesa.getMesaPrincipal() != null ? mesa.getMesaPrincipal().getId() : null
        ))
        .toList();
    }

    public List<MesaDTO> obtenerMesasPorMesaPrincipal(Integer mesaPrincipalId){
        return mesaRepository.findByMesaPrincipal_IdAndActivoTrue(mesaPrincipalId)
                .stream()
                .map( mesa -> new MesaDTO(
                    mesa.getId(),
                    mesa.getArea() != null ? mesa.getArea().getId() : null,
                    mesa.getNombre(),
                    mesa.getEstatus(),
                    mesa.getMesaPrincipal() != null ? mesa.getMesaPrincipal().getId() : null
                ))
                .toList();
    }

    @Transactional
    public void removerMesaPrincipal(List<Integer> mesaIds){
        if(mesaIds == null || mesaIds.isEmpty()){ return; }
        mesaRepository.removerMesaPrincipal(mesaIds, MesaEstatus.DISPONIBLE);
    }

    @Transactional
    public void agregarMesaPrincipal(Integer mesaPrincipalId, List<Integer> mesasIds){
        Mesa mesaPrincipal = mesaRepository.findById(mesaPrincipalId).orElseThrow(()-> new RuntimeException("Mesa principal no encontrada"));

        mesaRepository.agregarMesaPrincipal(mesaPrincipal, mesasIds, MesaEstatus.UNIDA);
    }

    @Transactional
    public void cambiarComandaDeMesa(Integer idMesaOrigen, Integer idMesaDestino) {

        if(mesaRepository.existsByMesaPrincipalId(idMesaOrigen)){
            throw new IllegalStateException(
                "No se puede cambiar de mesa porque la mesa origen tiene mesas unidas"
            );
        }

        Comanda comanda = comandaRepository
                            .findByMesaIdAndEstatus(idMesaOrigen, ComandaEstatus.CURSO)
                            .orElseThrow(()-> new RuntimeException("No existe comanda en curso en la mesa origen"));
        
        comandaRepository.cambiarMesa(comanda.getId(), idMesaDestino);

        Mesa mesaOrigen = mesaRepository.findById(idMesaOrigen).orElseThrow(() -> new RuntimeException("Mesa Origen no encontrada"));
        mesaOrigen.setNombre(quitarDescripcion(mesaOrigen.getNombre()));
        mesaOrigen.setEstatus(MesaEstatus.DISPONIBLE);
        mesaRepository.save(mesaOrigen);


        Mesa mesaDestino= mesaRepository.findById(idMesaDestino).orElseThrow(() -> new RuntimeException("Mesa Destino no encontrada"));
        mesaDestino.setEstatus(MesaEstatus.OCUPADO);
        mesaRepository.save(mesaDestino);
    }

    private String quitarDescripcion(String nombreMesa) {
        int indice = nombreMesa.indexOf(" - ");
        return indice != -1
                ? nombreMesa.substring(0, indice)
                : nombreMesa;
    }

}
