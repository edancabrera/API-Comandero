package com.crov.comandero.dto;

public class CambiarMesaDTO {
    private Integer idMesaOrigen;
    private Integer idMesaDestino;

    public Integer getIdMesaOrigen() {
        return idMesaOrigen;
    }
    public void setIdMesaOrigen(Integer idMesaOrigen) {
        this.idMesaOrigen = idMesaOrigen;
    }
    public Integer getIdMesaDestino() {
        return idMesaDestino;
    }
    public void setIdMesaDestino(Integer idMesaDestino) {
        this.idMesaDestino = idMesaDestino;
    }
}