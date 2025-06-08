package com.bibliotecavirtual.persistence;

import com.bibliotecavirtual.model.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAOImpl implements LibroDAO{

    @Override
    public void agregarLibro(Libro libro) throws Exception {

        String sql = "INSERT INTO libro (titulo, autor, genero, descripcion, pdf_url, portada_url, precio, puntuacion_promedio, cantidad_valoraciones, fecha_publicacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        int filasAfectadas=DBHelper.manejarEntidad(sql, libro.getTitulo(), libro.getAutor(), libro.getGenero(), libro.getDescripcion(), libro.getPdf_url(), libro.getPortada_url(), libro.getPrecio(), libro.getPuntuacion_promedio(), libro.getCantidad_valoraciones(), Date.valueOf(libro.getFecha_publicacion()));

        if(filasAfectadas ==0){

            throw new Exception("No se pudo registrar el libro");
        }
    }

    @Override
    public void editarLibro(Libro libro) throws Exception {

        String sql = "UPDATE libro SET titulo = ?, autor = ?, genero = ?, descripcion = ?, pdf_url = ?, portada_url = ?, precio = ?, puntuacion_promedio = ?, cantidad_valoraciones = ?, fecha_publicacion = ? WHERE id_libro = ?;";

        int filaAfectada = DBHelper.manejarEntidad(sql, libro.getTitulo(), libro.getAutor(), libro.getGenero(), libro.getDescripcion(), libro.getPdf_url(), libro.getPortada_url(), libro.getPrecio(), libro.getPuntuacion_promedio(), libro.getCantidad_valoraciones(), Date.valueOf(libro.getFecha_publicacion()), libro.getId());

        if(filaAfectada==0){

            throw new Exception("No se pudo actualizar el libro. Verifica si el ID existe.");
        }
    }

    @Override
    public void eliminarLibro(int id_libro) throws Exception {

        String sql = "DELETE FROM libro WHERE id_libro = ?;";

        int filasAfectadas = DBHelper.manejarEntidad(sql, id_libro);

        if(filasAfectadas == 0){

            throw new Exception("No se pudo eliminar el libro");
        }
    }

    @Override
    public void actualizarPuntuacionYValoracion(int id_libro, float promedio, int cantidad_valoraciones) throws Exception {

        String sql = "UPDATE libro SET puntuacion_promedio = ?, cantidad_valoraciones = ? WHERE id_libro = ?";

        DBHelper.manejarEntidad(sql, promedio, cantidad_valoraciones, id_libro);
    }

    @Override
    public Libro buscarLibroPorId(int id_libro) throws Exception {

        String sql = "SELECT * FROM libro WHERE id_libro = ?;";
        Libro libro = null;

        try(Connection conn = DBConexion.establecerConexion();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, id_libro);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){

                libro = new Libro(rs.getInt("id_libro"), rs.getString("titulo"), rs.getString("autor"), rs.getString("genero"), rs.getString("descripcion"), rs.getString("pdf_url"), rs.getString("portada_url"), rs.getInt("precio"), rs.getFloat("puntuacion_promedio"), rs.getInt("cantidad_valoraciones"), rs.getDate("fecha_publicacion").toLocalDate());
            }

        }catch (SQLException e){

            throw new Exception("Error al buscar libro por ID: " + e.getMessage(), e);
        }

        if(libro ==null){

            throw new Exception("No se encontró un libro con ID: " + id_libro);
        }
        return libro;
    }

    @Override
    public List<Libro> buscarLibrosPorTitulo(String titulo) throws Exception {

        String sql = "SELECT * FROM libro WHERE titulo LIKE ?";

        return buscarLibrosPorCampo(sql, "%" + titulo + "%");
    }

    @Override
    public List<Libro> buscarLibrosPorAutor(String autor) throws Exception {

        String sql = "SELECT * FROM libro WHERE autor LIKE ?";

        return buscarLibrosPorCampo(sql, "%" + autor + "%");
    }

    @Override
    public List<Libro> buscarLibrosPorGenero(String genero) throws Exception {

        String sql = "SELECT * FROM libro WHERE genero LIKE ?";

        return buscarLibrosPorCampo(sql, "%" + genero + "%");
    }

    private Libro mapearLibro(ResultSet rs) throws Exception{
        Libro libro = new Libro(rs.getInt("id_libro"), rs.getString("titulo"), rs.getString("autor"), rs.getString("genero"), rs.getString("descripcion"), rs.getString("pdf_url"), rs.getString("portada_url"), rs.getInt("precio"), rs.getFloat("puntuacion_promedio"), rs.getInt("cantidad_valoraciones"), rs.getDate("fecha_publicacion").toLocalDate());

        return libro;
    }

    private List<Libro> buscarLibrosPorCampo(String sql, String campo) throws Exception{

        List<Libro> libros = new ArrayList<>();

        try(Connection conn = DBConexion.establecerConexion();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, campo);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                libros.add(mapearLibro(rs));
            }

        }catch(SQLException e){

            throw new Exception("Error al buscar libros: " + e.getMessage(), e);
        }
        return libros;
    }
}
