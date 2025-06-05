package com.bibliotecavirtual.persistence;

import com.bibliotecavirtual.model.Cliente;
import com.bibliotecavirtual.model.Notificacion;
import com.bibliotecavirtual.model.Usuario;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NotificacionDAOImpl implements NotificacionDAO{

    @Override
    public void crearNotificacion(Notificacion notificacion) throws Exception {
        String sql = "INSERT INTO notificacion (id_usuario, tipo, mensaje, fecha_envio, es_leido) VALUES (?, ?, ?, ?, ?);";

        DBHelper.manejarEntidad(sql, notificacion.getCliente().getId(), notificacion.getTipo(), notificacion.getMensaje(), Date.valueOf(notificacion.getFecha_envio()), notificacion.isEs_leido());
    }

    @Override
    public List<Notificacion> obtenerNotificacionesPorUsuario(int id_usuario) throws Exception {

        List<Notificacion> notificaciones = new ArrayList<>();

        String sql = "SELECT * FROM notificacion WHERE id_usuario = ? ORDER BY fecha_envio DESC;";

        ResultSet rs = DBHelper.ejecutarConsulta(sql, id_usuario);

        while(rs.next()){

            notificaciones.add(mapearNotificacion(rs));
        }
        return notificaciones;
    }

    @Override
    public void marcarNotificacionComoLeida(int id_notificacion) throws Exception {

        String sql = "UPDATE notificacion SET es_leido = TRUE WHERE id_notificacion = ?;";

        DBHelper.manejarEntidad(sql, id_notificacion);

    }

    @Override
    public void eliminarNotificacion(int id_notificacion) throws Exception {

        String sql = "DELETE FROM notificacion WHERE id_notificacion = ?;";

        DBHelper.manejarEntidad(sql, id_notificacion);

    }

    private Notificacion mapearNotificacion(ResultSet rs) throws Exception{

        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();

        Usuario usuario = usuarioDAO.buscarUsuarioPorId(rs.getInt("id_usuario"));

        return new Notificacion(rs.getInt("id_notificacion"), (Cliente) usuario, rs.getString("tipo"), rs.getString("mensaje"), rs.getDate("fecha_envio").toLocalDate(), rs.getBoolean("es_leido"));
    }
}
