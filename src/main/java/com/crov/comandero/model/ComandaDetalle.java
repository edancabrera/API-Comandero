package com.crov.comandero.model;

import jakarta.persistence.*;

@Entity
@Table(name = "gv_comanda_detalle")
public class ComandaDetalle {
    public ComandaDetalle(){}

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_comanda", nullable = false)
    private Comanda comanda;

    @ManyToOne
    @JoinColumn(name = "id_platillo")
    private Producto platillo;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "costo")
    private Double costo;

    @Column(name = "iva")
    private Double iva;

    @Column(name = "estatus_cocina")
    private Integer estatusCocina;

    @Column(name = "persona")
    private Integer persona;

    @Column(name = "comentario")
    private String comentarios;

    //getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Producto getPlatillo() {
        return platillo;
    }

    public void setPlatillo(Producto platillo) {
        this.platillo = platillo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Integer getEstatusCocina() {
        return estatusCocina;
    }

    public void setEstatusCocina(Integer estatusCocina) {
        this.estatusCocina = estatusCocina;
    }

    public Integer getPersona() {
        return persona;
    }

    public void setPersona(Integer persona) {
        this.persona = persona;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    
    
}
