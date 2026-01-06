package com.crov.comandero.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crov.comandero.repository.ComplementosCategoriaPlatilloRespository;

@Service
public class ComplementosCategoriaPlatilloService {
    private final ComplementosCategoriaPlatilloRespository complementosCategoriaPlatilloRespository;

    public ComplementosCategoriaPlatilloService(ComplementosCategoriaPlatilloRespository complementosCategoriaPlatilloRespository){
        this.complementosCategoriaPlatilloRespository = complementosCategoriaPlatilloRespository;
    }

    public List<String> obtenerComplementos(Integer idCategoria) {
        return complementosCategoriaPlatilloRespository.findByIdCategoriaPlatilloAndActivoTrue(idCategoria)
                    .stream()
                    .map(ccp -> ccp.getComplementosPlatillo().getDescripsion())
                    .toList();
    }
}
