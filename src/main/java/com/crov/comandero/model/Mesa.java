package com.crov.comandero.model;

import jakarta.persistence.*;

@Entity
@Table(name = "gv_mesa")
public class Mesa {
    public Mesa(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_area", nullable = false)
    private Area area;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "clientes_maximos")
    private Integer clientesMaximos;

    @Column(name = "activo")
    private Boolean activo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estatus")
    private MesaEstatus estatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mesa_principal")
    private Mesa mesaPrincipal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
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

    public Mesa getMesaPrincipal() {
        return mesaPrincipal;
    }

    public void setMesaPrincipal(Mesa mesaPrincipal) {
        this.mesaPrincipal = mesaPrincipal;
    }
}