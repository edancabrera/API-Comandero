package com.crov.comandero.model;

import jakarta.persistence.*;

@Entity
@Table(name = "gv_complementos_platillo")
public class ComplementosPlatillo {
    public ComplementosPlatillo(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "activo")
    private Boolean activo;

    @Column(name = "descripsion")
    private String descripsion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getDescripsion() {
        return descripsion;
    }

    public void setDescripsion(String descripsion) {
        this.descripsion = descripsion;
    }
}
