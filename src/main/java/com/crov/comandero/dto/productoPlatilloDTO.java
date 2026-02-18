package com.crov.comandero.dto;

import com.crov.comandero.model.Menu;

public class ProductoPlatilloDTO {
    public ProductoPlatilloDTO(){}

    public ProductoPlatilloDTO(Integer idProducto, String nombre, Double precio1, Integer idCategoriaPlatillo, String nombreCategoria, Menu menu){
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio1 = precio1;
        this.idCategoriaPlatillo = idCategoriaPlatillo;
        this.nombreCategoria = nombreCategoria;
        this.menu = menu;
    }

    private Integer idProducto;
    private String nombre;
    private Double precio1;
    private Integer idCategoriaPlatillo;
    private String nombreCategoria;
    private Menu menu;

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

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}