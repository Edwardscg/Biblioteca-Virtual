package com.bibliotecavirtual.model;

import java.time.LocalDate;

public class Valoracion {

    private int id;
    private Cliente cliente;
    private Libro libro;
    private int puntuacion;
    private String comentario;
    private LocalDate fecha_valoracion;

    public Valoracion(int id, Cliente cliente, Libro libro, int puntuacion, String comentario, LocalDate fecha_valoracion) {
        this.id = id;
        this.cliente = cliente;
        this.libro = libro;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.fecha_valoracion = fecha_valoracion;
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

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFecha_valoracion() {
        return fecha_valoracion;
    }

    public void setFecha_valoracion(LocalDate fecha_valoracion) {
        this.fecha_valoracion = fecha_valoracion;
    }
}
