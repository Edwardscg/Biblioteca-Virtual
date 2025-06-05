package com.bibliotecavirtual.model;

public class Favorito {

    private int id;
    private Cliente cliente;
    private Libro libro;
    private String estado; // pendiente, terminado

    public Favorito(int id, Cliente cliente, Libro libro, String estado) {
        this.id = id;
        this.cliente = cliente;
        this.libro = libro;
        this.estado = estado;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
