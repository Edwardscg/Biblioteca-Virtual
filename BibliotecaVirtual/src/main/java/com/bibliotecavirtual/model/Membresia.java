package com.bibliotecavirtual.model;

import java.time.LocalDate;

public class Membresia {

    private int id;
    private Cliente cliente;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int costo;
    private EstadoMembresia estado; // activa o finalizada

    public Membresia(int id, Cliente cliente, LocalDate fechaInicio, LocalDate fechaFin, int costo, EstadoMembresia estado) {
        this.id = id;
        this.cliente = cliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.costo = costo;
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public EstadoMembresia getEstado() {
        return estado;
    }

    public void setEstado(EstadoMembresia estado) {
        this.estado = estado;
    }
}
