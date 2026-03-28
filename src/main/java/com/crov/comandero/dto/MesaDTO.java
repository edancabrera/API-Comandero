package com.crov.comandero.dto;

import java.util.List;

import com.crov.comandero.model.MesaEstatus;

public class MesaDTO {

    private Integer id;
    private Integer areaId;
    private String nombre;
    private MesaEstatus estatus;
    private Integer mesaPrincipalId;
    private List<Integer> mesasHijasIds;

    public MesaDTO(){}

    public MesaDTO(Integer id, Integer areaId, String nombre, MesaEstatus estatus, Integer mesaPrincipalId){
        this.id = id;
        this.areaId = areaId;
        this.nombre = nombre;
        this.estatus = estatus;
        this.mesaPrincipalId = mesaPrincipalId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public MesaEstatus getEstatus() {
        return estatus;
    }

    public void setEstatus(MesaEstatus estatus) {
        this.estatus = estatus;
    }

    public Integer getMesaPrincipalId() {
        return mesaPrincipalId;
    }

    public void setMesaPrincipalId(Integer mesaPrincipalId) {
        this.mesaPrincipalId = mesaPrincipalId;
    }

    public List<Integer> getMesasHijasIds() {
        return mesasHijasIds;
    }

    public void setMesasHijasIds(List<Integer> mesasHijasIds) {
        this.mesasHijasIds = mesasHijasIds;
    }

}
