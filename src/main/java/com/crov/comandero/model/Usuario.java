package com.crov.comandero.model;

import jakarta.persistence.*;

@Entity
@Table(name = "gv_usuario")
public class Usuario {
    public Usuario(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idu")
    private Integer idu;

    @Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellidos")
	private String apellidos;
	
	@Column(name = "email")
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_cargo")
	private TipoCargo tipoCargo;
	
	@Column(name = "activo")
	private Boolean activo;
	
	@Column(name = "clave_comanda")
	private String claveComanda;

    //Getters y Setters
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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getClaveComanda() {
        return claveComanda;
    }

    public void setClaveComanda(String claveComanda) {
        this.claveComanda = claveComanda;
    }
}