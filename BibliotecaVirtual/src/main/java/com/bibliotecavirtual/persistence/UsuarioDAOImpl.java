package com.bibliotecavirtual.persistence;

import com.bibliotecavirtual.model.*;

import java.sql.ResultSet;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO{

    @Override
    public void registrarUsuario(Usuario usuario) throws Exception {

        String sql = "INSERT INTO usuario (nombre, correo, contrasena, tipo, monedas) VALUES (?, ?, ?, ?, ?);";

        Object monedas = (usuario instanceof Administrador) ? null:0;

        int filasAfectadas=DBHelper.manejarEntidad(sql, usuario.getNombre(), usuario.getCorreo(), usuario.getContrasena(), usuario.getTipo_usuario(), monedas);

        if(filasAfectadas ==0){

            throw new Exception("No se pudo registrar al usuario");
        }
    }

    @Override
    public void actualizarUsuario(Usuario usuario) throws Exception {

        boolean es_admin = usuario instanceof Administrador;

        String sql = es_admin ? "UPDATE usuario SET nombre = ?, correo = ?, contrasena = ?, tipo = ? WHERE id_usuario = ?" : "UPDATE usuario SET nombre = ?, correo = ?, contrasena = ?, tipo = ?, monedas = ? WHERE id_usuario = ?";

        int fila_afectada = es_admin ? DBHelper.manejarEntidad(sql, usuario.getNombre(), usuario.getCorreo(), usuario.getContrasena(), usuario.getTipo_usuario(), usuario.getId()) : DBHelper.manejarEntidad(sql, usuario.getNombre(), usuario.getCorreo(), usuario.getContrasena(), usuario.getTipo_usuario(), ((Cliente) usuario).getMonedas(), usuario.getId());

        if (fila_afectada == 0) {

            throw new Exception("No se pudo actualizar el usuario.");
        }
    }

    @Override
    public void actualizarMonedas(int id_usuario, int nuevas_monedas) throws Exception {

        String sql = "UPDATE usuario SET monedas = ? WHERE id_usuario = ?";

        DBHelper.manejarEntidad(sql, nuevas_monedas, id_usuario);
    }

    @Override
    public void eliminarUsuario(int id_usuario) throws Exception {

        String sql = "DELETE FROM usuario WHERE id_usuario = ?;";

        int filasAfectadas = DBHelper.manejarEntidad(sql, id_usuario);

        if(filasAfectadas == 0){

            throw new Exception("No se pudo eliminar al usuario");
        }
    }

    @Override
    public void cambiarContrasena(int id_usuario, String contrasenaActual, String nuevaContrasena) throws Exception {

        String sql = "UPDATE usuario SET contrasena = ? WHERE id_usuario = ? AND contrasena = ?";

        DBHelper.manejarEntidad(sql, nuevaContrasena, id_usuario, contrasenaActual);
    }

    @Override
    public Usuario buscarUsuarioPorId(int id_usuario) throws Exception {
        String sql ="SELECT * FROM usuario WHERE id_usuario = ?;";

        return DBHelper.obtenerEntidad(sql, this::mapearFilaUsuario, id_usuario);
    }

    @Override
    public Usuario buscarUsuarioPorCorreo(String correo) throws Exception {
        String sql ="SELECT * FROM usuario WHERE correo = ?;";

        return DBHelper.obtenerEntidad(sql, this::mapearFilaUsuario, correo);
    }

    @Override
    public Usuario loguearUsuario(String correo, String contrasena) throws Exception {

        String sql = "SELECT * FROM usuario WHERE correo = ? AND contrasena = ?;";

        return DBHelper.obtenerEntidad(sql, this::mapearFilaUsuario, correo, contrasena);
    }

    @Override
    public List<Usuario> buscarUsuariosPorTipo(String tipo_usuario) throws Exception {

        String sql ="SELECT * FROM usuario WHERE tipo = ?;";

        return DBHelper.obtenerListaEntidad(sql, this::mapearFilaUsuario, tipo_usuario);
    }

    private Usuario mapearFilaUsuario(ResultSet rs) throws Exception{

        String tipo_usuario = rs.getString("tipo");

        if(tipo_usuario.equals("normal")){

            return new ClienteNormal(rs.getInt("id_usuario"), rs.getString("nombre"), rs.getString("correo"), rs.getString("contrasena"), rs.getString("tipo"), rs.getInt("monedas"));

        } else if(tipo_usuario.equals("estudiante")){

            return new ClienteEstudiante(rs.getInt("id_usuario"), rs.getString("nombre"), rs.getString("correo"), rs.getString("contrasena"), rs.getString("tipo"), rs.getInt("monedas"));

        } else{

            return new Administrador(rs.getInt("id_usuario"), rs.getString("nombre"), rs.getString("correo"), rs.getString("contrasena"), rs.getString("tipo"));

        }
    }
}
