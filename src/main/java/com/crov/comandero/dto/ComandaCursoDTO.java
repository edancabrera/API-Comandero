package com.crov.comandero.dto;

public class ComandaCursoDTO {
    private Integer idComanda;
    private Integer idMesa;

    public ComandaCursoDTO() {}

    public ComandaCursoDTO(Integer idComanda, Integer idMesa) {
        this.idComanda = idComanda;
        this.idMesa = idMesa;
    }

    public Integer getIdComanda() {
        return idComanda;
    }
    public void setIdComanda(Integer idComanda) {
        this.idComanda = idComanda;
    }
    public Integer getIdMesa() {
        return idMesa;
    }
    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }
}
