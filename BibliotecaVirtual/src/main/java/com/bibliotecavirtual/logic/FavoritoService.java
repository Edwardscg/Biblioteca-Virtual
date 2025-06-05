package com.bibliotecavirtual.logic;

import com.bibliotecavirtual.model.Libro;
import com.bibliotecavirtual.persistence.FavoritoDAOImpl;

import java.util.List;

public class FavoritoService {

    private FavoritoDAOImpl favoritoDAO;

    public FavoritoService(){

        this.favoritoDAO = new FavoritoDAOImpl();
    }

    public void agregarFavorito(int id_usuario, int id_libro) throws Exception{

        favoritoDAO.agregarFavorito(id_usuario, id_libro);
    }

    public void eliminarFavorito(int id_usuario, int id_libro) throws Exception{

        favoritoDAO.eliminarFavorito(id_usuario, id_libro);
    }

    public List<Libro> obtenerFavoritosPorUsuario(int id_usuario) throws Exception{

        return favoritoDAO.obtenerFavoritosPorUsuario(id_usuario);
    }

    public boolean esFavorito(int id_usuario, int id_libro) throws Exception{

        return favoritoDAO.esLibroFavorito(id_usuario, id_libro);
    }

    // POSIBLES METODOS A AGREGAR EN EL FUTURO
    // CONTAR FAVORITOS POR LIBRO
    // LIMPIAR EN SU TOTALIDAD LIBROS FAVORITOS DE UN USUARIO
}
