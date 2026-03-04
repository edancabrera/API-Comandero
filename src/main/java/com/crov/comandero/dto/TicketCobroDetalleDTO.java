package com.crov.comandero.dto;

public class TicketCobroDetalleDTO {

    private String nombre;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;
    private Integer iva;
    
    public TicketCobroDetalleDTO() {}

    public TicketCobroDetalleDTO(String nombre, Integer cantidad, Double precioUnitario, Double subtotal, Integer iva) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.iva = iva;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public Double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
    public Integer getIva() {
        return iva;
    }
    public void setIva(Integer iva) {
        this.iva = iva;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
