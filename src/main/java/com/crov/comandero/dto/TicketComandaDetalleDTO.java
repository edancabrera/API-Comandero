package com.crov.comandero.dto;

public class TicketComandaDetalleDTO {

    private Integer persona;
    private Integer cantidad;
    private String nombre;
    private String menu;
    private String comentarios;
    
    public Integer getPersona() {
        return persona;
    }
    public void setPersona(Integer persona) {
        this.persona = persona;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getMenu() {
        return menu;
    }
    public void setMenu(String menu) {
        this.menu = menu;
    }
    public String getComentarios() {
        return comentarios;
    }
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

}
