package com.crov.comandero.dto;

import java.util.List;

public class ObtenerComandaDTO {
    private Integer idMesa;
    private Integer idMesero;
    private List<ObtenerComandaDetalleDTO> detalles;
    private Double total;

    public ObtenerComandaDTO() {}
    
    public ObtenerComandaDTO(Integer idMesa, Integer idMesero, List<ObtenerComandaDetalleDTO> detalles, Double total) {
        this.idMesa = idMesa;
        this.idMesero = idMesero;
        this.detalles = detalles;
        this.total = total;
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

    public List<ObtenerComandaDetalleDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<ObtenerComandaDetalleDTO> detalles) {
        this.detalles = detalles;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    

    

    

}
