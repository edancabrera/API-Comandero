package com.crov.comandero.dto;

public class ProductoPlatilloDTO {
    public ProductoPlatilloDTO(){}

    public ProductoPlatilloDTO(Integer idProducto, String nombre, Boolean activo, Double precio1, Boolean mostrarEnElMenu, Boolean platillo, Integer idCategoriaPlatillo){
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.activo = activo;
        this.precio1 = precio1;
        this.mostrarEnElMenu = mostrarEnElMenu;
        this.platillo = platillo;
        this.idCategoriaPlatillo = idCategoriaPlatillo;
    }

    private Integer idProducto;
    private String nombre;
    private Boolean activo;
    private Double precio1;
    private Boolean mostrarEnElMenu;
    private Boolean platillo;
    private Integer idCategoriaPlatillo;

    public Integer getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Boolean getActivo() {
        return activo;
    }
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    public Double getPrecio1() {
        return precio1;
    }
    public void setPrecio1(Double precio1) {
        this.precio1 = precio1;
    }
    public Boolean getMostrarEnElMenu() {
        return mostrarEnElMenu;
    }
    public void setMostrarEnElMenu(Boolean mostrarEnElMenu) {
        this.mostrarEnElMenu = mostrarEnElMenu;
    }
    public Boolean getPlatillo() {
        return platillo;
    }
    public void setPlatillo(Boolean platillo) {
        this.platillo = platillo;
    }
    public Integer getIdCategoriaPlatillo() {
        return idCategoriaPlatillo;
    }
    public void setIdCategoriaPlatillo(Integer idCategoriaPlatillo) {
        this.idCategoriaPlatillo = idCategoriaPlatillo;
    }

}

