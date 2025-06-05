package com.bibliotecavirtual.persistence;

import com.bibliotecavirtual.model.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlquilerDAOImpl implements AlquilerDAO{

    @Override
    public void registrarAlquiler(Alquiler alquiler) throws Exception {


        String sql = "INSERT INTO alquiler (id_usuario, id_libro, fecha_inicio, fecha_fin, estado, pagina_actual) VALUES (?, ?, ?, ?, ?, ?)";

        DBHelper.ejecutarConsulta(sql, alquiler.getCliente().getId(), alquiler.getLibro().getId(), alquiler.getFecha_inicio(), alquiler.getFecha_fin(), alquiler.getEstado(), alquiler.getPagina_actual());
    }

    @Override
    public void finalizarAlquiler(int id_alquiler) throws Exception {
        String sql = "UPDATE alquiler SET estado = 'finalizado' WHERE id_alquiler = ?";
        DBHelper.ejecutarConsulta(sql, id_alquiler);
    }

    @Override
    public void cancelarAlquiler(int id_alquiler) throws Exception {

        String sql = "UPDATE alquiler SET estado = 'cancelado' WHERE id_alquiler = ?";
        DBHelper.ejecutarConsulta(sql, id_alquiler);
    }

    @Override
    public Alquiler buscarAquilerPorId(int id_alquiler) throws Exception {

        String sql = "SELECT * FROM alquiler WHERE id_alquiler = ?";

        ResultSet rs = DBHelper.ejecutarConsulta(sql, id_alquiler);

        if(rs.next()){

            return mapearAlquiler(rs);
        }
        return null;
    }

    @Override
    public List<Alquiler> verAlquileresPorUsuario(int id_usuario) throws Exception {

        List<Alquiler> lista_alquiler = new ArrayList<>();

        String sql = "SELECT * FROM alquiler WHERE id_usuario = ?";

        ResultSet rs = DBHelper.ejecutarConsulta(sql, id_usuario);

        while(rs.next()){

            lista_alquiler.add(mapearAlquiler(rs));
        }
        return lista_alquiler;
    }

    @Override
    public List<Alquiler> verAlquileresActivosPorUsuario(int id_usuario) throws Exception {
        List<Alquiler> lista_alquiler = new ArrayList<>();

        String sql = "SELECT * FROM alquiler WHERE id_usuario = ? AND estado = 'activo'";

        ResultSet rs = DBHelper.ejecutarConsulta(sql, id_usuario);

        while(rs.next()){

            lista_alquiler.add(mapearAlquiler(rs));
        }
        return lista_alquiler;
    }

    @Override
    public List<Alquiler> verAlquileres() throws Exception {
        List<Alquiler> lista_alquileres = new ArrayList<>();

        String sql = "SELECT * FROM alquiler";

        ResultSet rs = DBHelper.ejecutarConsulta(sql);

        while (rs.next()){

            lista_alquileres.add(mapearAlquiler(rs));
        }
        return lista_alquileres;
    }

    @Override
    public void actualizarPaginaActual(int id_alquiler, int nueva_pagina) throws Exception {

        String sql = "UPDATE alquiler SET pagina_actual = ? WHERE id_alquiler = ?";

        DBHelper.ejecutarConsulta(sql, nueva_pagina, id_alquiler);
    }

    @Override
    public int obtnerPaginaActual(int id_alquiler) throws Exception {

        String sql = "SELECT pagina_actual FROM alquiler WHERE id_alquiler = ?";

        ResultSet rs = DBHelper.ejecutarConsulta(sql, id_alquiler);

        if(rs.next()){

            return rs.getInt("pagina_actual");
        }
        return 1;
    }

    @Override
    public boolean verificarALquilerActivo(int id_usuario, int id_libro) throws Exception {

        String sql = "SELECT COUNT(*) FROM alquiler WHERE id_usuario = ? AND id_libro = ? AND estado = 'activo'";

        ResultSet rs = DBHelper.ejecutarConsulta(sql, id_usuario, id_libro);

        if(rs.next()){

            return rs.getInt(1)>0;
        }
        return false;
    }

    private Alquiler mapearAlquiler(ResultSet rs) throws Exception{

        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        LibroDAOImpl libroDAO = new LibroDAOImpl();

        Usuario usuario = usuarioDAO.buscarUsuarioPorId(rs.getInt("id_usuario"));
        Libro libro = libroDAO.buscarLibroPorId(rs.getInt("id_libro"));

        return new Alquiler(rs.getInt("id_alquiler"), (Cliente) usuario, libro, rs.getDate("fecha_inicio").toLocalDate(), rs.getDate("fechaFin").toLocalDate(), EstadoAlquiler.valueOf(rs.getString("estado")), rs.getInt("pagina_actual"));
    }
}
