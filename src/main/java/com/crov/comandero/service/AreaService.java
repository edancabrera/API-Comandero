package com.crov.comandero.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crov.comandero.model.Area;
import com.crov.comandero.repository.AreaRepository;

@Service
public class AreaService {
    private final AreaRepository areaRepository;

    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public List<Area> listarAreas(){
        return areaRepository.findByActivoTrue();
    }
}