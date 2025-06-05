package com.bibliotecavirtual.persistence;

import com.bibliotecavirtual.model.Cliente;
import com.bibliotecavirtual.model.MovimientoMoneda;
import com.bibliotecavirtual.model.Usuario;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MovimientoMonedaDAOImpl implements MovimientoMonedaDAO{

    @Override
    public void registrarMovimiento(MovimientoMoneda movimiviento) throws Exception {

        String sql = "INSERT INTO movimiento_moneda (id_usuario, tipo_movimiento, monto, fecha) VALUES (?, ?, ?, ?);";

        DBHelper.manejarEntidad(sql, movimiviento.getCliente().getId(), movimiviento.getTipo_movimiento(), movimiviento.getMonto(), Date.valueOf(movimiviento.getFecha_movimiento()));
    }

    @Override
    public List<MovimientoMoneda> verMovimientosPorUsuario(int id_usuario) throws Exception {

        List<MovimientoMoneda> movimientos = new ArrayList<>();

        String sql = "SELECT * FROM movimiento_moneda WHERE id_usuario = ? ORDER BY fecha DESC;";

        ResultSet rs = DBHelper.ejecutarConsulta(sql, id_usuario);

        while (rs.next()){

            movimientos.add(mapearMovimiento(rs));
        }
        return movimientos;
    }

    @Override
    public List<MovimientoMoneda> verMovimientos() throws Exception {

        List<MovimientoMoneda> movimientos = new ArrayList<>();

        String sql = "SELECT * FROM movimiento_moneda ORDER BY fecha DESC;";

        ResultSet rs = DBHelper.ejecutarConsulta(sql);

        while(rs.next()){

            movimientos.add(mapearMovimiento(rs));
        }

        return movimientos;
    }

    private MovimientoMoneda mapearMovimiento(ResultSet rs) throws Exception{

        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();

        Usuario usuario = usuarioDAO.buscarUsuarioPorId(rs.getInt("id_usuario"));

        return new MovimientoMoneda(rs.getInt("id_movimiento"), (Cliente) usuario, rs.getString("tipo_movimiento"), rs.getInt("monto"), rs.getDate("fecha").toLocalDate());
    }
}
