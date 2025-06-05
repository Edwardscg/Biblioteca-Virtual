package com.bibliotecavirtual.logic;

import com.bibliotecavirtual.model.Libro;
import com.bibliotecavirtual.persistence.LibroDAOImpl;

import java.util.List;

public class LibroService {

    private final LibroDAOImpl libroDAO;

    public LibroService() {
        this.libroDAO = new LibroDAOImpl();
    }

    public void agregarLibro(Libro libro) throws Exception{

        validarLibro(libro, false);

        libroDAO.agregarLibro(libro);
    }

    public void editarLibro(Libro libro) throws Exception{

        validarLibro(libro, true);

        libroDAO.editarLibro(libro);
    }

    public void eliminarLibro(int id_libro) throws Exception{

        if(id_libro <= 0){

            throw new IllegalArgumentException("ID de libro inválido.");
        }

        libroDAO.eliminarLibro(id_libro);
    }

    public Libro buscarLibroporId(int id_libro) throws Exception{

        if(id_libro <= 0){

            throw new IllegalArgumentException("ID de libro inválido.");
        }

        return libroDAO.buscarLibroPorId(id_libro);
    }

    public List<Libro> buscarLibrosPorTitulo(String titulo) throws Exception{

        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }

        return libroDAO.buscarLibrosPorTitulo(titulo);
    }

    public List<Libro> buscarLibrosPorAutor(String autor) throws Exception{

        if (autor == null || autor.trim().isEmpty()) {
            throw new IllegalArgumentException("El autor no puede estar vacío.");
        }

        return libroDAO.buscarLibrosPorAutor(autor);
    }

    public List<Libro> buscarLibrosPorGenero(String genero) throws Exception{

        if (genero == null || genero.trim().isEmpty()) {
            throw new IllegalArgumentException("El género no puede estar vacío.");
        }

        return libroDAO.buscarLibrosPorGenero(genero);
    }

    private void validarLibro(Libro libro, boolean requiere_id) throws Exception {
        if (libro == null) {
            throw new IllegalArgumentException("El libro no puede ser nulo.");
        }
        if (requiere_id && libro.getId() <= 0) {
            throw new IllegalArgumentException("ID de libro inválido.");
        }
        if (libro.getTitulo() == null || libro.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("El título es obligatorio.");
        }
        if (libro.getAutor() == null || libro.getAutor().trim().isEmpty()) {
            throw new IllegalArgumentException("El autor es obligatorio.");
        }
        if (libro.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        if (libro.getPuntutacion_promedio() < 0.0 || libro.getPuntutacion_promedio() > 5.0) {
            throw new IllegalArgumentException("La puntuación debe estar entre 0 y 5.");
        }
        if (libro.getCantidad_valoraciones() < 0) {
            throw new IllegalArgumentException("La cantidad de valoraciones no puede ser negativa.");
        }
        if (libro.getFecha_publicacion() == null) {
            throw new IllegalArgumentException("La fecha de publicación es obligatoria.");
        }
    }
}
