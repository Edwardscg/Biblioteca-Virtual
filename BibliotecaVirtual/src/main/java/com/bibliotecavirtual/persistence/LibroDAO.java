package com.bibliotecavirtual.persistence;

import com.bibliotecavirtual.model.Libro;

import java.util.List;

public interface LibroDAO {

    void agregarLibro(Libro libro) throws Exception;

    void editarLibro(Libro libro) throws Exception;

    void eliminarLibro(int id_libro) throws Exception;

    void actualizarPuntuacionYValoracion(int id_libro, float promedio, int cantidad_valoraciones) throws Exception;

    Libro buscarLibroPorId(int id_libro) throws Exception;

    List<Libro> buscarLibrosPorTitulo(String titulo) throws Exception;

    List<Libro> buscarLibrosPorAutor(String autor) throws Exception;

    List<Libro> buscarLibrosPorGenero(String genero) throws Exception;
}
