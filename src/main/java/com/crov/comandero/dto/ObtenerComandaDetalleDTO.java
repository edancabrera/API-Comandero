package com.crov.comandero.dto;

public class ObtenerComandaDetalleDTO {
    private Integer id;
    private Integer idComanda;
    private Integer idPlatillo;
    private Integer cantidad;
    private String comentarios;
    private Integer persona;
    private Integer idCategoriaPlatillo;
    private String nombre;
    private Double precio;
    private Integer estatusCocina;

    public ObtenerComandaDetalleDTO() {}
    
    public ObtenerComandaDetalleDTO(Integer id, Integer idComanda, Integer idPlatillo, Integer cantidad, String comentarios, Integer persona,
            Integer idCategoriaPlatillo, String nombre, Double precio, Integer estatusCocina) {
        this.id = id;
        this.idComanda = idComanda;
        this.idPlatillo = idPlatillo;
        this.cantidad = cantidad;
        this.comentarios = comentarios;
        this.persona = persona;
        this.idCategoriaPlatillo = idCategoriaPlatillo;
        this.nombre = nombre;
        this.precio = precio;
        this.estatusCocina = estatusCocina;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(Integer idComanda) {
        this.idComanda = idComanda;
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getEstatusCocina() {
        return estatusCocina;
    }

    public void setEstatusCocina(Integer estatusCocina) {
        this.estatusCocina = estatusCocina;
    }
    

}
