package com.crov.comandero.dto;

import java.util.List;
import java.util.Map;

public class TicketComandaDTO {
    private String tipo;
    private String mesa;
    private String mesero;
    private String fecha;

    private Map<String, Map<String, List<TicketComandaDetalleDTO>>> detalle;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public String getMesero() {
        return mesero;
    }

    public void setMesero(String mesero) {
        this.mesero = mesero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Map<String, Map<String, List<TicketComandaDetalleDTO>>> getDetalle() {
        return detalle;
    }

    public void setDetalle(Map<String, Map<String, List<TicketComandaDetalleDTO>>> detalle) {
        this.detalle = detalle;
    }

}
