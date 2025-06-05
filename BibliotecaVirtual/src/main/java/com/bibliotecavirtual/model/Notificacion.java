package com.bibliotecavirtual.model;

import java.time.LocalDate;

public class Notificacion {

    private int id;
    private Cliente cliente;
    private String tipo; // vencimiento, recordatorio, compra (monedas, libro o membresia)
    private String mensaje;
    private LocalDate fecha_envio;
    private boolean es_leido;

    public Notificacion(int id, Cliente cliente, String tipo, String mensaje, LocalDate fecha_envio, boolean es_leido) {
        this.id = id;
        this.cliente = cliente;
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.fecha_envio = fecha_envio;
        this.es_leido = es_leido;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDate getFecha_envio() {
        return fecha_envio;
    }

    public void setFecha_envio(LocalDate fecha_envio) {
        this.fecha_envio = fecha_envio;
    }

    public boolean isEs_leido() {
        return es_leido;
    }

    public void setEs_leido(boolean es_leido) {
        this.es_leido = es_leido;
    }
}
