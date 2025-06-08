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

        return DBHelper.obtenerEntidad(sql, rs -> true, id_usuario, id_libro) !=null;
    }

    @Override
    public List<CompraLibro> verComprasPorUsuario(int id_usuario) throws Exception {

        String sql = "SELECT * FROM compra_libro WHERE id_usuario = ?;";

        return DBHelper.obtenerListaEntidad(sql, this::mapearCompra, id_usuario);
    }

    @Override
    public List<CompraLibro> verCompras() throws Exception {

        String sql = "SELECT * FROM compra_libro;";

        return DBHelper.obtenerListaEntidad(sql, this::mapearCompra);
    }

    private CompraLibro mapearCompra(ResultSet rs) throws Exception{

        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        LibroDAOImpl libroDAO = new LibroDAOImpl();

        Usuario usuario = usuarioDAO.buscarUsuarioPorId(rs.getInt("id_usuario"));
        Libro libro = libroDAO.buscarLibroPorId(rs.getInt("id_libro"));

        return new CompraLibro(rs.getInt("id_compra_libro"), (Cliente) usuario, libro, rs.getDate("fecha_compra").toLocalDate(), rs.getInt("monto"));
    }
}
