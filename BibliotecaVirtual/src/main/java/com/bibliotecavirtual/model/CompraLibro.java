package com.bibliotecavirtual.model;

import java.time.LocalDate;

public class CompraLibro {

    private int id;
    private Cliente cliente;
    private Libro libro;
    private LocalDate fecha_Compra;
    private int costo;

    public CompraLibro(int id, Cliente cliente, Libro libro, LocalDate fecha_Compra, int costo) {
        this.id = id;
        this.cliente = cliente;
        this.libro = libro;
        this.fecha_Compra = fecha_Compra;
        this.costo = costo;
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

    public LocalDate getFecha_Compra() {
        return fecha_Compra;
    }

    public void setFecha_Compra(LocalDate fecha_Compra) {
        this.fecha_Compra = fecha_Compra;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }
}
