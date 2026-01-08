package com.crov.comandero.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @OneToMany(mappedBy = "complementosPlatillo")
    @JsonIgnore
    private List<ComplementosCategoriaPlatillo> categorias;

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

    public List<ComplementosCategoriaPlatillo> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<ComplementosCategoriaPlatillo> categorias) {
        this.categorias = categorias;
    }
}
