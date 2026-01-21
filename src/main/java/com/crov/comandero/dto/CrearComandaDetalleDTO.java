package com.crov.comandero.dto;

public class CrearComandaDetalleDTO {
    private Integer idPlatillo;
    private Integer cantidad;
    private String comentarios;
    private Integer persona;
    private Integer idCategoriaPlatillo;
    private String nombre;
    
    public CrearComandaDetalleDTO() {}

    public CrearComandaDetalleDTO(Integer idPlatillo, Integer cantidad, String comentarios, Integer persona, Integer idCategoriaPlatillo, String nombre) {
        this.idPlatillo = idPlatillo;
        this.cantidad = cantidad;
        this.comentarios = comentarios;
        this.persona = persona;
        this.idCategoriaPlatillo = idCategoriaPlatillo;
        this.nombre = nombre;
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

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Integer getPersona() {
        return persona;
    }

    public void setPersona(Integer persona) {
        this.persona = persona;
    }

    public Integer getIdCategoriaPlatillo() {
        return idCategoriaPlatillo;
    }

    public void setIdCategoriaPlatillo(Integer idCategoriaPlatillo) {
        this.idCategoriaPlatillo = idCategoriaPlatillo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
