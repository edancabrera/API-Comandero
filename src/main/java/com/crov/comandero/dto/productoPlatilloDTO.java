package com.crov.comandero.dto;

public class ProductoPlatilloDTO {
    public ProductoPlatilloDTO(){}

    public ProductoPlatilloDTO(Integer idProducto, String nombre, Double precio1, Integer idCategoriaPlatillo){
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio1 = precio1;
        this.idCategoriaPlatillo = idCategoriaPlatillo;
    }

    private Integer idProducto;
    private String nombre;
    private Double precio1;
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
    public Double getPrecio1() {
        return precio1;
    }
    public void setPrecio1(Double precio1) {
        this.precio1 = precio1;
    }
    public Integer getIdCategoriaPlatillo() {
        return idCategoriaPlatillo;
    }
    public void setIdCategoriaPlatillo(Integer idCategoriaPlatillo) {
        this.idCategoriaPlatillo = idCategoriaPlatillo;
    }
}