package com.crov.comandero.model;

import jakarta.persistence.*;

@Entity
@Table(name = "gv_producto")
public class Producto {
    public Producto(){}

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "idproducto")
    private Integer idProducto;

    @Column(name = "cod_barras")
    private String codBarras;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "costo")
    private Double costo;

    @Column(name = "activo")
    private Boolean activo;

    @Column(name = "precio1")
    private Double precio1;

    @Column(name = "mostrar_en_el_menu")
    private Boolean mostrarEnElMenu;

    @Column(name = "platillo")
    private Boolean platillo;

    @Column(name = "id_categoria_platillo")
    private Integer idCategoriaPlatillo;

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
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
