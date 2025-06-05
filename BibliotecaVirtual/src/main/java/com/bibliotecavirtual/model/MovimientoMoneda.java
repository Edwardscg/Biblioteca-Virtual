package com.bibliotecavirtual.model;

import java.time.LocalDate;

public class MovimientoMoneda {

    private int id;
    private Cliente cliente;
    private String tipo_movimiento; // recarga, gasto
    private int monto;
    private LocalDate fecha_movimiento;

    public MovimientoMoneda(int id, Cliente cliente, String tipo_movimiento, int monto, LocalDate fecha_movimiento) {
        this.id = id;
        this.cliente = cliente;
        this.tipo_movimiento = tipo_movimiento;
        this.monto = monto;
        this.fecha_movimiento = fecha_movimiento;
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

    public String getTipo_movimiento() {
        return tipo_movimiento;
    }

    public void setTipo_movimiento(String tipo_movimiento) {
        this.tipo_movimiento = tipo_movimiento;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public LocalDate getFecha_movimiento() {
        return fecha_movimiento;
    }

    public void setFecha_movimiento(LocalDate fecha_movimiento) {
        this.fecha_movimiento = fecha_movimiento;
    }
}
