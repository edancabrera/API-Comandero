package com.crov.comandero.dto;

import com.crov.comandero.model.TipoCargo;

public class UsuarioDTO {
    private Integer idu;
    private String nombre;
    private String apellidos;
    private String email;
    private TipoCargo tipoCargo;

    public UsuarioDTO(){}

    public UsuarioDTO(Integer idu, String nombre, String apellidos, String email, TipoCargo tipoCargo) {
        this.idu = idu;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.tipoCargo = tipoCargo;
    }

    public Integer getIdu() {
        return idu;
    }

    public void setIdu(Integer idu) {
        this.idu = idu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoCargo getTipoCargo() {
        return tipoCargo;
    }

    public void setTipoCargo(TipoCargo tipoCargo) {
        this.tipoCargo = tipoCargo;
    }

    
}
