package com.bibliotecavirtual.logic;

import com.bibliotecavirtual.model.Alquiler;
import com.bibliotecavirtual.model.Cliente;
import com.bibliotecavirtual.model.Libro;
import com.bibliotecavirtual.model.Usuario;
import com.bibliotecavirtual.persistence.AlquilerDAOImpl;

import java.util.ArrayList;
import java.util.List;

public class AlquilerService {

    private  AlquilerDAOImpl alquilerDAO;

    public AlquilerService(){

        this.alquilerDAO = new AlquilerDAOImpl();
    }

    public void regitrarAlquiler(Alquiler alquiler) throws Exception{

        int id_usuario = alquiler.getCliente().getId();
        int id_libro = alquiler.getLibro().getId();
        int costo_alquiler = alquiler.getLibro().getPrecio();

        if(alquilerDAO.verificarAlquilerActivo(id_usuario, id_libro)){

            throw new Exception("Ya tienes un alquiler activo de este libro.");
        }

        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = usuarioService.buscarPorId(id_usuario);
        Cliente cliente = (Cliente) usuario;

        if(cliente.getMonedas()<costo_alquiler){

            throw new Exception("No tienes suficientes monedas para alquilar este libro.");
        }

        int nuevasMonedas = cliente.getMonedas() - costo_alquiler;
        cliente.setMonedas(nuevasMonedas);
        usuarioService.actualizarMonedas(id_usuario, nuevasMonedas);

        MovimientoMonedaService movimientoMonedaService = new MovimientoMonedaService();
        movimientoMonedaService.gastarMonedas(id_usuario, costo_alquiler);

        alquilerDAO.registrarAlquiler(alquiler);

        String tipo_notificacion = "Alquiler";
        String mensaje = "Has alquilado el libro "+ alquiler.getLibro().getTitulo() + ". Se descontarom " + costo_alquiler + " monedas.";

        NotificacionService notificacionService = new NotificacionService();
        notificacionService.enviarNotificacion(id_usuario, tipo_notificacion, mensaje);
    }

    public void finalizarAlquiler (int id_alquiler) throws Exception{

        Alquiler alquiler = alquilerDAO.buscarAlquilerPorId(id_alquiler);

        alquilerDAO.finalizarAlquiler(id_alquiler);

        String tipo_notificacion = "Vencimiento de Alquiler";
        String mensaje = "Ha finalizado el alquiler del libro "+ alquiler.getLibro().getTitulo() + ".";

        NotificacionService notificacionService = new NotificacionService();
        notificacionService.enviarNotificacion(alquiler.getCliente().getId(), tipo_notificacion, mensaje);
    }
    // POSIBLE BORRADO
    public void cancelarAlquiler (int id_alquiler) throws Exception{

        Alquiler alquiler = alquilerDAO.buscarAlquilerPorId(id_alquiler);

        alquilerDAO.cancelarAlquiler(id_alquiler);

        String tipo_notificacion = "Cancelamiento de Alquiler";
        String mensaje = "Se ha cancelado el alquiler del libro "+ alquiler.getLibro().getTitulo() + ".";

        NotificacionService notificacionService = new NotificacionService();
        notificacionService.enviarNotificacion(alquiler.getCliente().getId(), tipo_notificacion, mensaje);
    }
    // POSIBLE BORRADO
    public Alquiler buscarAlquilerPorId(int id_alquiler) throws Exception{

        return alquilerDAO.buscarAlquilerPorId(id_alquiler);
    }

    public List<Alquiler> verAlquileresPorUsuario(int id_usuario) throws Exception{

        return alquilerDAO.verAlquileresPorUsuario(id_usuario);
    }

    public List<Alquiler> verAlquileresActivosPorUsuario(int id_usuario) throws Exception{

        return alquilerDAO.verAlquileresActivosPorUsuario(id_usuario);
    }

    public List<Alquiler> verAlquileres() throws Exception{

        return alquilerDAO.verAlquileres();
    }

    public void actualizarPagina(int id_alquiler, int nueva_pagina) throws Exception{

        alquilerDAO.actualizarPaginaActual(id_alquiler, nueva_pagina);
    }

    public int obtenerPaginaActual(int id_alquiler) throws Exception{

        return alquilerDAO.obtnerPaginaActual(id_alquiler);
    }

    public boolean verificarAlquilerActivoDeUsuario(int id_usuario, int id_libro) throws Exception{

        return alquilerDAO.verificarAlquilerActivo(id_usuario, id_libro);
    }

    public List<Libro> obtenerLibrosAqluiladosPorUsuario(int id_usuario)throws Exception{

        List<Alquiler> alquilers = alquilerDAO.verAlquileresPorUsuario(id_usuario);
        List<Libro> librosAlquilados = new ArrayList<>();

        for(Alquiler alquiler: alquilers){

            librosAlquilados.add(alquiler.getLibro());
        }

        return librosAlquilados;
    }
}
