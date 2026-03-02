package com.crov.comandero.dto;

import java.util.List;

public class TicketCobroDTO {
    private String mesero;
    private String mesa;
    private String fecha;
    private List<TicketCobroDetalleDTO> detalle;
    private Double total;

    public TicketCobroDTO() {}

    public TicketCobroDTO(String mesero, String mesa, String fecha, List<TicketCobroDetalleDTO> detalle, Double total) {
        this.mesero = mesero;
        this.mesa = mesa;
        this.fecha = fecha;
        this.detalle = detalle;
        this.total = total;
    }

    public String getMesero() {
        return mesero;
    }

    public void setMesero(String mesero) {
        this.mesero = mesero;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    
    public List<TicketCobroDetalleDTO> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<TicketCobroDetalleDTO> detalle) {
        this.detalle = detalle;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
