package com.bibliotecavirtual.logic;

import com.bibliotecavirtual.model.Cliente;
import com.bibliotecavirtual.model.MovimientoMoneda;
import com.bibliotecavirtual.model.Usuario;
import com.bibliotecavirtual.persistence.MovimientoMonedaDAOImpl;
import com.bibliotecavirtual.persistence.UsuarioDAOImpl;

import java.time.LocalDate;
import java.util.List;

public class MovimientoMonedaService {

    private final MovimientoMonedaDAOImpl movimientoMonedaDAO;
    private final UsuarioDAOImpl usuarioDAO;

    public MovimientoMonedaService() {
        this.movimientoMonedaDAO = new MovimientoMonedaDAOImpl();
        this.usuarioDAO = new UsuarioDAOImpl();
    }

    public void agregarMonedas(int id_usuario, int monto) throws Exception{

        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero.");
        }

        Usuario usuario = usuarioDAO.buscarUsuarioPorId(id_usuario);

        if (!(usuario instanceof Cliente cliente)) {
            throw new Exception("Solo los clientes pueden tener saldo de monedas.");
        }

        cliente.setMonedas(cliente.getMonedas()+monto);
        usuarioDAO.actualizarUsuario(cliente);

        MovimientoMoneda movimiento= new MovimientoMoneda(0, cliente, "recarga", monto, LocalDate.now());

        movimientoMonedaDAO.registrarMovimiento(movimiento);
    }

    public void gastarMonedas(int id_usuario, int monto) throws Exception{

        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero.");
        }

        Usuario usuario = usuarioDAO.buscarUsuarioPorId(id_usuario);
        Cliente cliente = (Cliente) usuario;

        if (cliente.getMonedas() < monto) {
            throw new Exception("Saldo insuficiente para realizar el gasto.");
        }

        cliente.setMonedas(cliente.getMonedas()-monto);
        usuarioDAO.actualizarUsuario(cliente);

        MovimientoMoneda movimiento = new MovimientoMoneda(0, cliente, "gasto", monto, LocalDate.now());

        movimientoMonedaDAO.registrarMovimiento(movimiento);
    }

    public List<MovimientoMoneda> verMovimientosPorUsuario(int id_usuario) throws Exception{

        return movimientoMonedaDAO.verMovimientosPorUsuario(id_usuario);
    }

    public List<MovimientoMoneda> verMovimientos()throws Exception{

        return movimientoMonedaDAO.verMovimientos();
    }
}
