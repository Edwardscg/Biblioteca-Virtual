package com.bibliotecavirtual.persistence;

import com.bibliotecavirtual.model.Usuario;

import java.util.List;

public interface UsuarioDAO {

    void registrarUsuario(Usuario usuario) throws Exception;

    void eliminarUsuario(int id_usuario) throws Exception;

    void actualizarUsuario(Usuario usuario) throws Exception;

    void actualizarMonedas(int id_usuario, int nuevasMonedas) throws Exception;

    Usuario loguearUsuario(String correo, String contrasena) throws Exception;

    void cambiarContrasena(int id_usuario, String contrasena_actual, String nueva_contrasena) throws Exception;

    Usuario buscarUsuarioPorId(int _usuario) throws Exception;

    Usuario buscarUsuarioPorCorreo(String correo) throws Exception;

    List<Usuario> buscarUsuariosPorTipo(String tipo_usuario) throws Exception;
}
