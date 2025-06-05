package com.bibliotecavirtual.logic;

import com.bibliotecavirtual.model.Cliente;
import com.bibliotecavirtual.model.Notificacion;
import com.bibliotecavirtual.model.Usuario;
import com.bibliotecavirtual.persistence.NotificacionDAOImpl;
import com.bibliotecavirtual.persistence.UsuarioDAOImpl;

import java.time.LocalDate;
import java.util.List;

public class NotificacionService {

    private final NotificacionDAOImpl notificacionDAO;
    private final UsuarioDAOImpl usuarioDAO;

    public NotificacionService() {
        this.notificacionDAO = new NotificacionDAOImpl();
        this.usuarioDAO = new UsuarioDAOImpl();
    }

    public void enviarNotificacion(int id_usuario, String tipo, String mensaje) throws Exception {

        Usuario usuario = usuarioDAO.buscarUsuarioPorId(id_usuario);
        Cliente cliente = (Cliente) usuario;

        Notificacion notificacion = new Notificacion(0, cliente, tipo, mensaje, LocalDate.now(), false);

        notificacionDAO.crearNotificacion(notificacion);

        String asunto = "Nueva notificación de: " + tipo;
        String cuerpo = mensaje;

        CorreoService correoService = new CorreoService();
        correoService.enviarCorreo(cliente.getCorreo(), asunto, cuerpo);
    }

    public List<Notificacion> verNotificacionesPorUsuario(int id_usuario) throws Exception{

        return notificacionDAO.obtenerNotificacionesPorUsuario(id_usuario);
    }

    public void marcarComoLeida(int id_notificacion) throws Exception{

        notificacionDAO.marcarNotificacionComoLeida(id_notificacion);
    }

    public void eliminarNotificacion(int id_Notificacion) throws Exception{

        notificacionDAO.eliminarNotificacion(id_Notificacion);
    }

    public void marcarTodasComoLeidas(int id_usuario) throws Exception{

        List<Notificacion> notificacions = notificacionDAO.obtenerNotificacionesPorUsuario(id_usuario);

        for(Notificacion notificacion : notificacions){

            if(!notificacion.isEs_leido()){

                notificacionDAO.marcarNotificacionComoLeida(notificacion.getId());
            }
        }
    }
}
