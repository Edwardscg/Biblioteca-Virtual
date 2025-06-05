package com.bibliotecavirtual.persistence;

import com.bibliotecavirtual.model.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO{

    @Override
    public void registrarUsuario(Usuario usuario) throws Exception {

        String sql = "INSERT INTO usuario (nombre, correo, contrasena, tipo, monedas) VALUES ('?', '?', '?', '?', '?');";

        Object monedasParametro;

        if(usuario instanceof Administrador){

            monedasParametro = null;
        }else{

            monedasParametro =0;
        }

        int filasAfectadas=DBHelper.manejarEntidad(sql, usuario.getNombre(), usuario.getCorreo(), usuario.getContrasena(), usuario.getTipo_usuario(), monedasParametro);

        if(filasAfectadas ==0){

            throw new Exception("No se pudo registrar al usuario");
        }
    }

    @Override
    public void actualizarUsuario(Usuario usuario) throws Exception {

        String sql;
        int filaAfectada;
        Object monedasParametro;

        if (usuario instanceof Administrador) {
            sql = "UPDATE usuario SET nombre = ?, correo = ?, contrasena = ?, tipo = ? WHERE id_usuario = ?";
            monedasParametro = null;
        } else {
            sql = "UPDATE usuario SET nombre = ?, correo = ?, contrasena = ?, tipo = ?, monedas = ? WHERE id_usuario = ?";
            monedasParametro = ((Cliente) usuario).getMonedas();
        }

        if (usuario instanceof Administrador) {
            filaAfectada = DBHelper.manejarEntidad(sql, usuario.getNombre(), usuario.getCorreo(), usuario.getContrasena(), usuario.getTipo_usuario(), usuario.getId());
        } else {
            filaAfectada = DBHelper.manejarEntidad(sql, usuario.getNombre(), usuario.getCorreo(), usuario.getContrasena(), usuario.getTipo_usuario(), monedasParametro, usuario.getId());
        }

        if (filaAfectada == 0) {
            throw new Exception("No se pudo actualizar el usuario.");
        }

    }

    @Override
    public void actualizarMonedas(int id_usuario, int nuevasMonedas) throws Exception {

        String sql = "UPDATE usuario SET monedas = ? WHERE id_usuario = ?";

        DBHelper.manejarEntidad(sql, nuevasMonedas, id_usuario);
    }

    @Override
    public Usuario loguearUsuario(String correo, String contrasena) throws Exception {

        String sql = "SELECT * FROM usuario WHERE correo = ? AND contrasena = ?;";

        return buscarUsuarioPorCampo(sql, correo, contrasena);
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

        return buscarUsuarioPorCampo(sql, id_usuario);
    }

    @Override
    public Usuario buscarUsuarioPorCorreo(String correo) throws Exception {
        String sql ="SELECT * FROM usuario WHERE correo = ?;";

        return buscarUsuarioPorCampo(sql, correo);
    }

    @Override
    public List<Usuario> buscarUsuariosPorTipo(String tipo_usuario) throws Exception {

        List<Usuario> usuarios = new ArrayList<>();

        String sql ="SELECT * FROM usuario WHERE tipo = ?;";

        ResultSet rs = DBHelper.ejecutarConsulta(sql, tipo_usuario);
        while(rs.next()){

            usuarios.add(buscarUsuariosPorCampo(rs));
        }

        return usuarios;
    }

    private Usuario buscarUsuarioPorCampo(String sql, Object... parametro) throws Exception{

        Usuario usuario = null;

        try(ResultSet rs = DBHelper.ejecutarConsulta(sql, parametro)){

            if(rs.next()){

                String tipo_usuario = rs.getString("tipo");

                if(tipo_usuario.equals("normal")){

                    usuario = new ClienteNormal(rs.getInt("id_usuario"), rs.getString("nombre"), rs.getString("correo"), rs.getString("contrasena"), rs.getString("tipo"), rs.getInt("monedas"));
                } else if(tipo_usuario.equals("estudiante")){

                    usuario = new ClienteEstudiante(rs.getInt("id_usuario"), rs.getString("nombre"), rs.getString("correo"), rs.getString("contrasena"), rs.getString("tipo"), rs.getInt("monedas"));
                } else{

                    usuario = new Administrador(rs.getInt("id_usuario"), rs.getString("nombre"), rs.getString("correo"), rs.getString("contrasena"), rs.getString("tipo"));
                }
            }
        }

        return usuario;
    }

    private Usuario buscarUsuariosPorCampo(ResultSet rs) throws Exception{

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
