package com.bibliotecavirtual.model;

import java.time.LocalDate;

public class Libro {
    private int id;
    private String titulo;
    private String autor;
    private String genero;
    private String descripcion;
    private String pdf_url;
    private String portada_url;
    private int precio;
    private float puntutacion_promedio;
    private int cantidad_valoraciones;
    private LocalDate fecha_publicacion;

    public Libro(int id, String titulo, String autor, String genero, String descripcion, String pdf_url, String portada_url, int precio, float puntutacion_promedio, int cantidad_valoraciones, LocalDate fecha_publicacion) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.descripcion = descripcion;
        this.pdf_url = pdf_url;
        this.portada_url = portada_url;
        this.precio = precio;
        this.puntutacion_promedio = puntutacion_promedio;
        this.cantidad_valoraciones = cantidad_valoraciones;
        this.fecha_publicacion = fecha_publicacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPdf_url() {
        return pdf_url;
    }

    public void setPdf_url(String pdf_url) {
        this.pdf_url = pdf_url;
    }

    public String getPortada_url() {
        return portada_url;
    }

    public void setPortada_url(String portada_url) {
        this.portada_url = portada_url;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public float getPuntutacion_promedio() {
        return puntutacion_promedio;
    }

    public void setPuntutacion_promedio(float puntutacion_promedio) {
        this.puntutacion_promedio = puntutacion_promedio;
    }

    public int getCantidad_valoraciones() {
        return cantidad_valoraciones;
    }

    public void setCantidad_valoraciones(int cantidad_valoraciones) {
        this.cantidad_valoraciones = cantidad_valoraciones;
    }

    public LocalDate getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(LocalDate fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }
}
