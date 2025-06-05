package com.bibliotecavirtual.model;

public class ClienteEstudiante extends Cliente{
    private static final int descuento_alquiler = 2;

    public ClienteEstudiante(int id, String nombre, String correo, String contrasena, String tipo_usuario, int monedas) {
        super(id, nombre, correo, contrasena, tipo_usuario, monedas);
    }

    public int getDescuento_prestamo() {
        return descuento_alquiler;
    }
}
