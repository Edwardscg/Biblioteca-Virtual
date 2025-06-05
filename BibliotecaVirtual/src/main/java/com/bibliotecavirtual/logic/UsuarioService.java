package com.bibliotecavirtual.logic;

import com.bibliotecavirtual.model.Usuario;
import com.bibliotecavirtual.persistence.UsuarioDAOImpl;

import java.util.List;

public class UsuarioService {

    private final UsuarioDAOImpl usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAOImpl();
    }

    public void registrarUsuario(Usuario usuario) throws Exception{

        if (usuario == null || usuario.getNombre().isBlank() || usuario.getCorreo().isBlank() || usuario.getContrasena().isBlank()) {
            throw new IllegalArgumentException("Datos del usuario incompletos.");
        }

        Usuario usuario_existente = usuarioDAO.buscarUsuarioPorCorreo(usuario.getCorreo());
        if (usuario_existente != null) {
            throw new Exception("Ya existe un usuario registrado con ese correo.");
        }

        usuarioDAO.registrarUsuario(usuario);
    }

    public void actualizarUsuario(Usuario usuario) throws Exception{

        if (usuario == null || usuario.getId() <= 0 || usuario.getCorreo().isBlank() || usuario.getNombre().isBlank()) {

            throw new IllegalArgumentException("Datos inválidos.");
        }

        usuarioDAO.actualizarUsuario(usuario);
    }

    public void actualizarMonedas(int id_usuario, int nuevasMonedas) throws Exception{

        usuarioDAO.actualizarMonedas(id_usuario, nuevasMonedas);
    }

    public Usuario loguearUsuario(String correo, String contrasena) throws Exception{

        if(correo == null || contrasena == null || correo.isBlank() || contrasena.isBlank()){

            throw new IllegalArgumentException("Correo o contraseña inválidos.");
        }

        Usuario usuario = usuarioDAO.loguearUsuario(correo, contrasena);

        if(usuario == null){

            throw new Exception("Credenciales incorrectas.");
        }
        return usuario;
    }

    public Usuario buscarPorId(int id_usuario) throws Exception {

        if (id_usuario <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        return usuarioDAO.buscarUsuarioPorId(id_usuario);
    }

    public Usuario buscarUsuarioPorCorreo(String correo) throws Exception{

        if (correo == null || correo.isBlank()) {
            throw new IllegalArgumentException("Correo inválido.");
        }

        return usuarioDAO.buscarUsuarioPorCorreo(correo);
    }

    public List<Usuario> buscarUsuarioPorTipo(String tipo_usuario) throws Exception{

        return usuarioDAO.buscarUsuariosPorTipo(tipo_usuario);
    }

    public void cambiarContrasena(int id_usuario, String contrasena_actual, String nueva_contrasena) throws Exception {

        if (id_usuario <= 0 || contrasena_actual == null || nueva_contrasena == null || contrasena_actual.isBlank() ||nueva_contrasena.isBlank()) {
            throw new IllegalArgumentException("Datos inválidos para cambiar contraseña.");
        }

        usuarioDAO.cambiarContrasena(id_usuario, contrasena_actual, nueva_contrasena);
    }

    public void recuperarContrasena(String correo) throws Exception {

        Usuario usuario = usuarioDAO.buscarUsuarioPorCorreo(correo);

        if (usuario == null) {
            throw new Exception("Usuario no encontrado con ese correo");
        }

        String asunto = "Recuperacion de Contraseña";
        String cuerpo = "Hola " + usuario.getNombre() + ",\n\nTu contraseña actual es: " + usuario.getContrasena() + "\n\nRecuerda cambiarla si no reconoces esta solicitud.";

        CorreoService correoService = new CorreoService();
        correoService.enviarCorreo(usuario.getCorreo(), asunto, cuerpo);
    }

    public void eliminarUsuario(int id_usuario) throws Exception {

        if (id_usuario <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        usuarioDAO.eliminarUsuario(id_usuario);
    }
}
