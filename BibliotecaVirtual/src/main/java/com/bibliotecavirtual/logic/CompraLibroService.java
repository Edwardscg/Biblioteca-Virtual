package com.bibliotecavirtual.logic;

import com.bibliotecavirtual.model.Cliente;
import com.bibliotecavirtual.model.CompraLibro;
import com.bibliotecavirtual.model.Libro;
import com.bibliotecavirtual.model.Usuario;
import com.bibliotecavirtual.persistence.CompraLibroDAOImpl;

import java.util.ArrayList;
import java.util.List;

public class CompraLibroService {

    private CompraLibroDAOImpl compraLibroDAO;
    private UsuarioService usuarioService;
    private NotificacionService notificacionService;

    public CompraLibroService(){

        this.compraLibroDAO = new CompraLibroDAOImpl();
        this.usuarioService = new UsuarioService();
        this.notificacionService = new NotificacionService();
    }

    public void registrarCompra(CompraLibro compra) throws Exception{

        int id_usuario = compra.getCliente().getId();
        int costo = compra.getCosto();

        Usuario usuario = usuarioService.buscarPorId(id_usuario);
        Cliente cliente = (Cliente) usuario;

        if(compraLibroDAO.estaComprado(id_usuario, compra.getLibro().getId())){

            throw new Exception("Ya ha comprado este libro anteriormente.");
        }

        if(cliente.getMonedas()<costo){

            throw new Exception("No tiene monedas suficientes para comprar el libro.");
        }

        compraLibroDAO.registrarCompra(compra);

        int nuevasMonedas = cliente.getMonedas()-costo;
        usuarioService.actualizarMonedas(id_usuario, nuevasMonedas);

        String tipo_notificacion = "Compra de Libro";
        String mensaje = "Se ha comprado el libro "+ compra.getLibro().getTitulo() + ".";

        notificacionService.enviarNotificacion(id_usuario, tipo_notificacion, mensaje);
    }

    public boolean estaComprado(int id_usuario, int id_libro) throws Exception{

        return compraLibroDAO.estaComprado(id_usuario, id_libro);
    }

    public List<CompraLibro> verComprasPorUsuario(int id_usuario) throws Exception{

        return compraLibroDAO.verComprasPorUsuario(id_usuario);
    }

    public List<CompraLibro> verCompras() throws Exception{

        return compraLibroDAO.verCompras();
    }

    public List<Libro> obtenerLibrosCompradosPorUsuario(int id_usuario) throws Exception{

        List<CompraLibro> compras = compraLibroDAO.verComprasPorUsuario(id_usuario);
        List<Libro> librosComprados = new ArrayList<>();

        for(CompraLibro compra: compras){

            librosComprados.add(compra.getLibro());
        }

        return librosComprados;
    }
}
