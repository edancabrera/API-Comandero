package com.crov.comandero.dto;

public class CrearComandaDetalleDTO {
    private Integer idPlatillo;
    private Integer cantidad;
    private String comenatrio;
    private Integer persona;
    
    public CrearComandaDetalleDTO() {}

    public CrearComandaDetalleDTO(Integer idPlatillo, Integer cantidad, String comenatrio, Integer persona) {
        this.idPlatillo = idPlatillo;
        this.cantidad = cantidad;
        this.comenatrio = comenatrio;
        this.persona = persona;
    }

    public Integer getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(Integer idPlatillo) {
        this.idPlatillo = idPlatillo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getComenatrio() {
        return comenatrio;
    }

    public void setComenatrio(String comenatrio) {
        this.comenatrio = comenatrio;
    }

    public Integer getPersona() {
        return persona;
    }

    public void setPersona(Integer persona) {
        this.persona = persona;
    }

    
}
