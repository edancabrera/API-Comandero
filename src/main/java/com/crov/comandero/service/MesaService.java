package com.crov.comandero.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crov.comandero.model.Mesa;
import com.crov.comandero.repository.MesaRepository;

@Service
public class MesaService {
    private final MesaRepository mesaRepository;

    public MesaService(MesaRepository mesaRepository){
        this.mesaRepository = mesaRepository;
    }

    public List<Mesa> listarMesasPorArea(Integer idArea){
        return mesaRepository.findByIdArea(idArea);
    }
}
