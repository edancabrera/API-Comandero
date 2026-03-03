package com.crov.comandero.model;

import com.crov.comandero.model.converter.GiroComercialConverter;

import jakarta.persistence.*;

@Entity
@Table(name = "gv_empresa")
public class Empresa {
    public Empresa(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "razon_social")
    private String razonSocial;

    @Column(name = "rfc")
    private String rfc;

    @Column(name = "contacto")
    private String contacto;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "colonia")
    private String colonia;

    @Column(name = "estado")
    private String estado;

    @Column(name = "municipio")
    private String municipio;

    @Column(name = "cp")
    private String cp;

    @Column(name = "correo")
    private String correo;

    @Column(name = "tel")
    private String tel;

    @Column(name = "cel")
    private String cel;

    @Column(name = "activo")
    private Boolean activo;

    @Column(name = "giro_comercial")
    @Convert(converter = GiroComercialConverter.class)
    private GiroComercial giroComercial;

    @Column(name = "nombre_comercial")
    private String nombreComercial;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public GiroComercial getGiroComercial() {
        return giroComercial;
    }

    public void setGiroComercial(GiroComercial giroComercial) {
        this.giroComercial = giroComercial;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }
}