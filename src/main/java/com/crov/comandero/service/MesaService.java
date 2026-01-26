package com.crov.comandero.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crov.comandero.dto.MesaDTO;
import com.crov.comandero.model.Mesa;
import com.crov.comandero.repository.MesaRepository;

@Service
public class MesaService {
    private final MesaRepository mesaRepository;

    public MesaService(MesaRepository mesaRepository){
        this.mesaRepository = mesaRepository;
    }

    public List<MesaDTO> obtenerMesasPorArea(Integer idArea){
        return mesaRepository.findByAreaIdAndActivoTrue(idArea)
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

    public void agregarDescripcionAMesa(Integer idMesa, String descripcion){
        Mesa mesa = mesaRepository.findById(idMesa).orElseThrow(() -> new RuntimeException("Mesa no encontrada"));
        mesa.setNombre(descripcion);
        mesaRepository.save(mesa);
    }
}
