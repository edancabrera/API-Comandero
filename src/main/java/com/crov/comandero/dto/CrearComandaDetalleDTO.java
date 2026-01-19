package com.crov.comandero.dto;

public class CrearComandaDetalleDTO {
    private Integer idPlatillo;
    private Integer cantidad;
    private String comentarios;
    private Integer persona;
    
    public CrearComandaDetalleDTO() {}

    public CrearComandaDetalleDTO(Integer idPlatillo, Integer cantidad, String comentarios, Integer persona) {
        this.idPlatillo = idPlatillo;
        this.cantidad = cantidad;
        this.comentarios = comentarios;
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

    public String getComentarios() {
        return comentarios;
    }

    public void setComentario(String comentarios) {
        this.comentarios = comentarios;
    }

    public Integer getPersona() {
        return persona;
    }

    public void setPersona(Integer persona) {
        this.persona = persona;
    }
}
