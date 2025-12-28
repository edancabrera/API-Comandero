package com.crov.comandero.model;

import jakarta.persistence.*;

@Entity
@Table(name = "gv_mesa")
public class Mesa {
    public Mesa(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idu")
    private Integer id;

    @Column(name = "id_area")
    private Integer idArea;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "clientes_maximos")
    private Integer clientesMaximos;

    @Column(name = "activo")
    private Boolean activo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estatus")
    private MesaEstatus estatus;

    @Column(name = "id_mesa_principal")
    private Integer idMesaPrincipal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getClientesMaximos() {
        return clientesMaximos;
    }

    public void setClientesMaximos(Integer clientesMaximos) {
        this.clientesMaximos = clientesMaximos;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public MesaEstatus getEstatus() {
        return estatus;
    }

    public void setEstatus(MesaEstatus estatus) {
        this.estatus = estatus;
    }

    public Integer getIdMesaPrincipal() {
        return idMesaPrincipal;
    }

    public void setIdMesaPrincipal(Integer idMesaPrincipal) {
        this.idMesaPrincipal = idMesaPrincipal;
    }
}