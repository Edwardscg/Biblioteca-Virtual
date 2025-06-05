package com.bibliotecavirtual.persistence;

import com.bibliotecavirtual.model.Cliente;
import com.bibliotecavirtual.model.CompraLibro;
import com.bibliotecavirtual.model.Libro;
import com.bibliotecavirtual.model.Usuario;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CompraLibroDAOImpl implements CompraLibroDAO{

    @Override
    public void registrarCompra(CompraLibro compra) throws Exception {

        String sql = "INSERT INTO compra_libro (id_usuario, id_libro, fecha_compra, monto) VALUES (?, ?, ?, ?);";

        DBHelper.manejarEntidad(sql, compra.getCliente().getId(), compra.getLibro().getId(), Date.valueOf(compra.getFecha_Compra()), compra.getCosto());
    }

    @Override
    public boolean estaComprado(int id_usuario, int id_libro) throws Exception {

        String sql = "SELECT 1 FROM compra_libro WHERE id_usuario = ? AND id_libro = ? LIMIT 1;";

        ResultSet rs = DBHelper.ejecutarConsulta(sql, id_usuario, id_libro);
        return rs.next();
    }

    @Override
    public List<CompraLibro> verComprasPorUsuario(int id_usuario) throws Exception {

        List<CompraLibro> compras = new ArrayList<>();

        String sql = "SELECT * FROM compra_libro WHERE id_usuario = ?;";

        ResultSet rs = DBHelper.ejecutarConsulta(sql, id_usuario);

        while(rs.next()){

            compras.add(mapearCompra(rs));
        }
        return compras;
    }

    @Override
    public List<CompraLibro> verCompras() throws Exception {

        List<CompraLibro> compras = new ArrayList<>();

        String sql = "SELECT * FROM compra_libro;";

        ResultSet rs = DBHelper.ejecutarConsulta(sql);

        while(rs.next()){

            compras.add(mapearCompra(rs));
        }
        return compras;
    }

    private CompraLibro mapearCompra(ResultSet rs) throws Exception{

        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        LibroDAOImpl libroDAO = new LibroDAOImpl();

        Usuario usuario = usuarioDAO.buscarUsuarioPorId(rs.getInt("id_usuario"));
        Libro libro = libroDAO.buscarLibroPorId(rs.getInt("id_libro"));

        return new CompraLibro(rs.getInt("id_compra_libro"), (Cliente) usuario, libro, rs.getDate("fecha_compra").toLocalDate(), rs.getInt("monto"));
    }
}
