package com.crov.comandero.dto;

import java.util.List;

public class CrearComandaDTO {
    private Integer idMesa;
    private Integer idMesero;
    private List<CrearComandaDetalleDTO> detalles;

    public CrearComandaDTO() {}
    
    public CrearComandaDTO(Integer idMesa, Integer idMesero, List<CrearComandaDetalleDTO> detalles) {
        this.idMesa = idMesa;
        this.idMesero = idMesero;
        this.detalles = detalles;
    }

    public Integer getIdMesa() {
        return idMesa;
    }
    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }
    public Integer getIdMesero() {
        return idMesero;
    }
    public void setIdMesero(Integer idMesero) {
        this.idMesero = idMesero;
    }
    public List<CrearComandaDetalleDTO> getDetalles() {
        return detalles;
    }
    public void setDetalles(List<CrearComandaDetalleDTO> detalles) {
        this.detalles = detalles;
    }
}
