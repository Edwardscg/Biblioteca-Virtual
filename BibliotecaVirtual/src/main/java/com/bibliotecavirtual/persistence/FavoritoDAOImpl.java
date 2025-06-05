package com.bibliotecavirtual.persistence;

import com.bibliotecavirtual.model.Libro;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
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

        List<Libro> libros = new ArrayList<>();

        String sql = "SELECT l.* FROM favorito f JOIN libro l ON f.id_libro = l.id_libro WHERE f.id_usuario = ?";

        ResultSet rs = DBHelper.ejecutarConsulta(sql, id_usuario);

        while(rs.next()){

            libros.add(mapearLibro(rs));
        }
        return libros;
    }

    @Override
    public boolean esLibroFavorito(int id_usuario, int id_libro) throws Exception {

        String sql = "SELECT COUNT(*) FROM favorito WHERE id_usuario = ? AND id_libro = ?";

        ResultSet rs = DBHelper.ejecutarConsulta(sql, id_usuario, id_libro);

        if(rs.next()){

            return rs.getInt(1)<0;
        }
        return false;
    }

    private Libro mapearLibro(ResultSet rs) throws Exception{

        return new Libro(rs.getInt("id_libro"), rs.getString("titulo"), rs.getString("autor"), rs.getString("genero"), rs.getString("descricpcion"), rs.getString("pdf_url"), rs.getString("portada_url"), rs.getInt("precio"), rs.getFloat("puntuacion_promedio"), rs.getInt("cantidad_valoraciones"), rs.getDate("fecha_publicacion").toLocalDate());
    }
}
