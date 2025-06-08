package com.bibliotecavirtual.persistence;

import com.bibliotecavirtual.model.Alquiler;

import java.util.List;

public interface AlquilerDAO {

    void registrarAlquiler(Alquiler alquiler) throws Exception;

    void finalizarAlquiler(int id_alquiler) throws Exception;

    void cancelarAlquiler(int id_alquiler) throws Exception;

    Alquiler buscarAlquilerPorId(int id_alquiler) throws Exception;

    List<Alquiler> verAlquileresPorUsuario(int id_usuario) throws Exception;

    List<Alquiler> verAlquileresActivosPorUsuario(int id_usuario) throws Exception;

    List<Alquiler> verAlquileres() throws Exception;

    void actualizarPaginaActual(int id_alquiler, int nueva_pagina)throws Exception;

    int obtnerPaginaActual(int id_alquiler) throws Exception;

    boolean verificarAlquilerActivo(int id_usuario, int id_libro) throws Exception;

}
