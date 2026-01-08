package com.crov.comandero.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "gv_areas")
public class Area {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "numero_mesas")
    private Integer numeroMesas;

    @Column(name = "activo")
    private Boolean activo;

    @Column(name = "zona_fumadores")
    private Boolean zonaFumadores;

    @OneToMany(mappedBy = "area", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Mesa> mesas;

    public Area(){}

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getNumeroMesas() {
        return numeroMesas;
    }
    public void setNumeroMesas(Integer numeroMesas) {
        this.numeroMesas = numeroMesas;
    }
    public Boolean getActivo() {
        return activo;
    }
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    public Boolean getZonaFumadores() {
        return zonaFumadores;
    }
    public void setZonaFumadores(Boolean zonaFumadores) {
        this.zonaFumadores = zonaFumadores;
    }

    public List<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(List<Mesa> mesas) {
        this.mesas = mesas;
    }

}
