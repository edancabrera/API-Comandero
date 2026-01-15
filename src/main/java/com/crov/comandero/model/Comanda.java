package com.crov.comandero.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "gv_comanda")
public class Comanda {
    public Comanda(){}

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_mesero", nullable = false)
    private Usuario mesero;

    @ManyToOne
    @JoinColumn(name = "id_mesa", nullable = false)
    private Mesa mesa;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_cobro")
    private LocalDateTime fechaCobro;

    @Enumerated(EnumType.STRING)
    @Column(name = "estatus")
    private ComandaEstatus estatus;

    @Column(name = "activo")
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "id_usuario_cancelado")
    private Usuario usuarioCancelado;

    @Column(name = "fecha_cancelacion")
    private LocalDateTime fechaCancelacion;

    @Column(name = "extra")
    private Double extra;

    @Column(name = "extra_comentario")
    private String extraComentario;

    @ManyToOne
    @JoinColumn(name = "id_comanda_principal")
    private Comanda comandaPrincipal;

    //getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getMesero() {
        return mesero;
    }

    public void setMesero(Usuario mesero) {
        this.mesero = mesero;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(LocalDateTime fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    public ComandaEstatus getEstatus() {
        return estatus;
    }

    public void setEstatus(ComandaEstatus estatus) {
        this.estatus = estatus;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Usuario getUsuarioCancelado() {
        return usuarioCancelado;
    }

    public void setUsuarioCancelado(Usuario usuarioCancelado) {
        this.usuarioCancelado = usuarioCancelado;
    }

    public LocalDateTime getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(LocalDateTime fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public Double getExtra() {
        return extra;
    }

    public void setExtra(Double extra) {
        this.extra = extra;
    }

    public String getExtraComentario() {
        return extraComentario;
    }

    public void setExtraComentario(String extraComentario) {
        this.extraComentario = extraComentario;
    }

    public Comanda getComandaPrincipal() {
        return comandaPrincipal;
    }

    public void setComandaPrincipal(Comanda comandaPrincipal) {
        this.comandaPrincipal = comandaPrincipal;
    }
}