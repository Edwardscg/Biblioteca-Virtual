package com.bibliotecavirtual.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class RecuperacionContrasenaDAO {

    public void crearCodigoDeRecuperacion(int id_usuario, String codigo, LocalDateTime fechaExpiracion) throws Exception{

        String sql = "INSERT INTO recuperacion_contrasena (id_usuario, codigo, fecha_expiracion, usado) VALUES (?, ?, ?, false)";

        DBHelper.manejarEntidad(sql, id_usuario, codigo, Timestamp.valueOf(fechaExpiracion));
    }

    public boolean validarCodigo(int id_usuario, String codigo) throws SQLException{

        String sql = "SELECT usado, fecha_expiracion FROM recuperacion_contrasena WHERE id_usuario = ? AND codigo = ? AND usado = FALSE";

        ResultSet rs = DBHelper.ejecutarConsulta(sql, id_usuario, codigo);
        if(rs.next()){

                LocalDateTime fechaExpiracion = rs.getTimestamp("fecha_expiracion").toLocalDateTime();
            boolean usado = rs.getBoolean("usado");

            return !usado && fechaExpiracion.isAfter(LocalDateTime.now());
        }

        return false;
    }

    public void marcarCodigoComoUsado(int id_usuario, String codigo) throws SQLException{
        String sql = "UPDATE recuperacion_contrasena SET usado = true WHERE id_usuario = ? AND codigo = ?";

        DBHelper.manejarEntidad(sql, id_usuario, codigo);
    }
}
