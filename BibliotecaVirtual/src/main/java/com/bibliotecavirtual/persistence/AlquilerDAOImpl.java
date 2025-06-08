package com.bibliotecavirtual.persistence;

import com.bibliotecavirtual.model.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlquilerDAOImpl implements AlquilerDAO{

    private UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
    private LibroDAO libroDAO = new LibroDAOImpl();

    @Override
    public void registrarAlquiler(Alquiler alquiler) throws Exception {


        String sql = "INSERT INTO alquiler (id_usuario, id_libro, fecha_inicio, fecha_fin, estado, pagina_actual) VALUES (?, ?, ?, ?, ?, ?)";

        DBHelper.manejarEntidad(sql, alquiler.getCliente().getId(), alquiler.getLibro().getId(), Date.valueOf(alquiler.getFecha_inicio()), Date.valueOf(alquiler.getFecha_fin()), alquiler.getEstado(), alquiler.getPagina_actual());
    }

    @Override
    public void finalizarAlquiler(int id_alquiler) throws Exception {
        String sql = "UPDATE alquiler SET estado = 'finalizado' WHERE id_alquiler = ?";
        DBHelper.manejarEntidad(sql, id_alquiler);
    }

    @Override
    public void cancelarAlquiler(int id_alquiler) throws Exception {

        String sql = "UPDATE alquiler SET estado = 'cancelado' WHERE id_alquiler = ?";
        DBHelper.manejarEntidad(sql, id_alquiler);
    }

    @Override
    public Alquiler buscarAlquilerPorId(int id_alquiler) throws Exception {

        String sql = "SELECT * FROM alquiler WHERE id_alquiler = ?";

        return DBHelper.obtenerEntidad(sql, mapearAlquiler, id_alquiler);
    }

    @Override
    public List<Alquiler> verAlquileresPorUsuario(int id_usuario) throws Exception {

        String sql = "SELECT * FROM alquiler WHERE id_usuario = ?";

        return DBHelper.obtenerListaEntidad(sql, mapearAlquiler, id_usuario);
    }

    @Override
    public List<Alquiler> verAlquileresActivosPorUsuario(int id_usuario) throws Exception {
        List<Alquiler> lista_alquiler = new ArrayList<>();

        String sql = "SELECT * FROM alquiler WHERE id_usuario = ? AND estado = 'activo'";

        return DBHelper.obtenerListaEntidad(sql, mapearAlquiler, id_usuario);
    }

    @Override
    public List<Alquiler> verAlquileres() throws Exception {
        List<Alquiler> lista_alquileres = new ArrayList<>();

        String sql = "SELECT * FROM alquiler";

        return DBHelper.obtenerListaEntidad(sql, mapearAlquiler);
    }

    @Override
    public void actualizarPaginaActual(int id_alquiler, int nueva_pagina) throws Exception {

        String sql = "UPDATE alquiler SET pagina_actual = ? WHERE id_alquiler = ?";

        DBHelper.manejarEntidad(sql, nueva_pagina, id_alquiler);
    }

    @Override
    public int obtnerPaginaActual(int id_alquiler) throws Exception {

        String sql = "SELECT pagina_actual FROM alquiler WHERE id_alquiler = ?";

        Integer pagina_actual = DBHelper.obtenerEntidad(sql, rs -> rs.getInt("pagina_actual"), id_alquiler);

        return pagina_actual!=null ? pagina_actual: 1;
    }

    @Override
    public boolean verificarAlquilerActivo(int id_usuario, int id_libro) throws Exception {

        String sql = "SELECT COUNT(*) FROM alquiler WHERE id_usuario = ? AND id_libro = ? AND estado = 'activo'";

        Integer estado = DBHelper.obtenerEntidad(sql, rs -> rs.getInt(1), id_usuario, id_libro);

        return estado!=null && estado>0;
    }

    private RowMapper<Alquiler> mapearAlquiler = rs ->{

        Usuario usuario = usuarioDAO.buscarUsuarioPorId(rs.getInt("id_usuario"));
        Libro libro = libroDAO.buscarLibroPorId(rs.getInt("id_libro"));

        return new Alquiler(rs.getInt("id_alquiler"), (Cliente) usuario, libro, rs.getDate("fecha_inicio").toLocalDate(), rs.getDate("fechaFin").toLocalDate(), EstadoAlquiler.valueOf(rs.getString("estado")), rs.getInt("pagina_actual"));
    };
}
