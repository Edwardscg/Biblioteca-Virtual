package com.bibliotecavirtual.persistence;

import com.bibliotecavirtual.model.Notificacion;

import java.util.List;

public interface NotificacionDAO {

    void crearNotificacion(Notificacion notificacion) throws Exception;

    List<Notificacion> obtenerNotificacionesPorUsuario(int id_usuario) throws Exception;

    void marcarNotificacionComoLeida(int id_notificacion) throws Exception;

    void eliminarNotificacion(int id_notificacion) throws Exception;

}
