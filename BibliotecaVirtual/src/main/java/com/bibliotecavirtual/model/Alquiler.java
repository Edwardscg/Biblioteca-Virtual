package com.bibliotecavirtual.model;

import java.time.LocalDate;

public class Alquiler {

    private int id;
    private Cliente cliente;
    private Libro libro;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private EstadoAlquiler estado; // prestado o devuelto
    private int pagina_actual;

    public Alquiler(int id, Cliente cliente, Libro libro, LocalDate fecha_inicio, LocalDate fecha_fin, EstadoAlquiler estado, int pagina_actual) {
        this.id = id;
        this.cliente = cliente;
        this.libro = libro;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.estado = estado;
        this.pagina_actual = pagina_actual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public EstadoAlquiler getEstado() {
        return estado;
    }

    public void setEstado(EstadoAlquiler estado) {
        this.estado = estado;
    }

    public int getPagina_actual() {
        return pagina_actual;
    }

    public void setPagina_actual(int pagina_actual) {
        this.pagina_actual = pagina_actual;
    }
}
