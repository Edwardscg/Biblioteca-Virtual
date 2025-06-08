package com.bibliotecavirtual.model;

public abstract class Usuario {

    protected int id;
    protected String nombre;
    protected String correo;
    protected String contrasena;
    protected String tipo_usuario; // administrador, cliente

    public Usuario(int id, String nombre, String correo, String contrasena, String tipo_usuario) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.tipo_usuario = tipo_usuario;
    }

    public int getId() { return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
}
