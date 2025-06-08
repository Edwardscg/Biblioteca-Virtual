package com.bibliotecavirtual.persistence;

import com.bibliotecavirtual.model.Libro;

import java.sql.ResultSet;
import java.util.List;

public class FavoritoDAOImpl implements FavoritoDAO{

    @Override
    public void agregarFavorito(int id_usuario, int id_libro) throws Exception {

        if(!esLibroFavorito(id_usuario, id_libro)){

            String sql = "INSERT INTO favorito (id_usuario, id_libro) VALUES (?, ?)";

            DBHelper.manejarEntidad(sql, id_usuario, id_libro);
        }
    }

    @Override
    public void eliminarFavorito(int id_usuario, int id_libro) throws Exception {

        String sql = "DELETE FROM favorito WHERE id_usuario = ? AND id_libro = ?";

        DBHelper.manejarEntidad(sql, id_usuario, id_libro);
    }

    @Override
    public List<Libro> obtenerFavoritosPorUsuario(int id_usuario) throws Exception {

        String sql = "SELECT l.* FROM favorito f JOIN libro l ON f.id_libro = l.id_libro WHERE f.id_usuario = ?";

        return DBHelper.obtenerListaEntidad(sql, this::mapearLibro, id_usuario);
    }

    @Override
    public boolean esLibroFavorito(int id_usuario, int id_libro) throws Exception {

        String sql = "SELECT 1 FROM favorito WHERE id_usuario = ? AND id_libro = ? LIMIT 1";

        return DBHelper.obtenerEntidad(sql, rs -> true, id_usuario, id_libro) != null;
    }

    private Libro mapearLibro(ResultSet rs) throws Exception{

        return new Libro(rs.getInt("id_libro"), rs.getString("titulo"), rs.getString("autor"), rs.getString("genero"), rs.getString("descricpcion"), rs.getString("pdf_url"), rs.getString("portada_url"), rs.getInt("precio"), rs.getFloat("puntuacion_promedio"), rs.getInt("cantidad_valoraciones"), rs.getDate("fecha_publicacion").toLocalDate());
    }
}
