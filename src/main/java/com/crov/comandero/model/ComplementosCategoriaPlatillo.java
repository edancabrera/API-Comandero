package com.crov.comandero.model;

import jakarta.persistence.*;

@Entity
@Table(name = "gv_complementos_categoria_platillo")
public class ComplementosCategoriaPlatillo {
    public ComplementosCategoriaPlatillo(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "activo")
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "id_categoria_platillo", nullable = false)
    private CategoriaPlatillo categoriaPlatillo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "id_complementos_platillo",
        referencedColumnName = "id"
    )
    private ComplementosPlatillo complementosPlatillo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public CategoriaPlatillo getCategoriaPlatillo() {
        return categoriaPlatillo;
    }

    public void setCategoriaPlatillo(CategoriaPlatillo categoriaPlatillo) {
        this.categoriaPlatillo = categoriaPlatillo;
    }

    public ComplementosPlatillo getComplementosPlatillo() {
        return complementosPlatillo;
    }

    public void setComplementosPlatillo(ComplementosPlatillo complementosPlatillo) {
        this.complementosPlatillo = complementosPlatillo;
    }

}