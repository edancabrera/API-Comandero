package com.crov.comandero.dto;

public class CrearComandaDetalleDTO {
    private Integer idPlatillo;
    private Integer cantidad;
    private String comentario;
    private Integer persona;
    private Integer estatusCocina;
    
    public CrearComandaDetalleDTO() {}

    public CrearComandaDetalleDTO(Integer idPlatillo, Integer cantidad, String comentario, Integer persona, Integer estatusCocina) {
        this.idPlatillo = idPlatillo;
        this.cantidad = cantidad;
        this.comentario = comentario;
        this.persona = persona;
        this.estatusCocina = estatusCocina;
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getPersona() {
        return persona;
    }

    public void setPersona(Integer persona) {
        this.persona = persona;
    }

    public Integer getEstatusCocina() {
        return estatusCocina;
    }

    public void setEstatusCocina(Integer estatusCocina) {
        this.estatusCocina = estatusCocina;
    }

    
}
