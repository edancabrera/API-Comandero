package com.crov.comandero.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "gv_parametros")
public class Parametros {
    public Parametros(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "impresora_bar")
    private String impresoraBar;

    @Column(name = "impresora_cocina")
    private String impresoraCocina;

    @Column(name = "impresora_admin")
    private String impresoraAdmin;

    @Column(name = "impresora_asador")
    private String impresoraAsador;

    @Column(name = "impresora_bar_papel")
    private Integer impresoraBarPapel;

    @Column(name = "impresora_cocina_papel")
    private Integer impresoraCocinaPapel;

    @Column(name = "impresora_admin_papel")
    private Integer impresoraAdminPapel;

    @Column(name = "impresora_asador_papel")
    private Integer impresoraAsadorPapel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImpresoraBar() {
        return impresoraBar;
    }

    public void setImpresoraBar(String impresoraBar) {
        this.impresoraBar = impresoraBar;
    }

    public String getImpresoraCocina() {
        return impresoraCocina;
    }

    public void setImpresoraCocina(String impresoraCocina) {
        this.impresoraCocina = impresoraCocina;
    }

    public String getImpresoraAdmin() {
        return impresoraAdmin;
    }

    public void setImpresoraAdmin(String impresoraAdmin) {
        this.impresoraAdmin = impresoraAdmin;
    }

    public String getImpresoraAsador() {
        return impresoraAsador;
    }

    public void setImpresoraAsador(String impresoraAsador) {
        this.impresoraAsador = impresoraAsador;
    }

    public Integer getImpresoraBarPapel() {
        return impresoraBarPapel;
    }

    public void setImpresoraBarPapel(Integer impresoraBarPapel) {
        this.impresoraBarPapel = impresoraBarPapel;
    }

    public Integer getImpresoraCocinaPapel() {
        return impresoraCocinaPapel;
    }

    public void setImpresoraCocinaPapel(Integer impresoraCocinaPapel) {
        this.impresoraCocinaPapel = impresoraCocinaPapel;
    }

    public Integer getImpresoraAdminPapel() {
        return impresoraAdminPapel;
    }

    public void setImpresoraAdminPapel(Integer impresoraAdminPapel) {
        this.impresoraAdminPapel = impresoraAdminPapel;
    }

    public Integer getImpresoraAsadorPapel() {
        return impresoraAsadorPapel;
    }

    public void setImpresoraAsadorPapel(Integer impresoraAsadorPapel) {
        this.impresoraAsadorPapel = impresoraAsadorPapel;
    }


}
