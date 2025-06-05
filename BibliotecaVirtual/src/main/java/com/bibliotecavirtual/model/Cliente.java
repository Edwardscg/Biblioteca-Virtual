package com.bibliotecavirtual.model;

public abstract class Cliente extends Usuario{
    protected int monedas;

    public Cliente(int id, String nombre, String correo, String contrasena, String tipo_usuario, int monedas) {
        super(id, nombre, correo, contrasena, tipo_usuario);
        this.monedas = monedas;
    }

    public int getMonedas() {
        return monedas;
    }

    public void setMonedas(int monedas) {
        this.monedas = monedas;
    }
}
