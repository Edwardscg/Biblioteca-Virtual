package com.bibliotecavirtual.persistence;

import com.bibliotecavirtual.model.Libro;

import java.util.List;

public interface FavoritoDAO {

    void agregarFavorito(int id_usuario, int id_libro) throws Exception;

    void eliminarFavorito(int id_usuario, int id_libro) throws Exception;

    List<Libro> obtenerFavoritosPorUsuario(int id_usuario) throws Exception;

    boolean esLibroFavorito(int id_usuario, int id_libro) throws Exception;
}
