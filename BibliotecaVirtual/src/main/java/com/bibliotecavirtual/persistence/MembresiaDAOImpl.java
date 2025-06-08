package com.bibliotecavirtual.persistence;

import com.bibliotecavirtual.model.Cliente;
import com.bibliotecavirtual.model.EstadoMembresia;
import com.bibliotecavirtual.model.Membresia;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MembresiaDAOImpl implements MembresiaDAO{

    @Override
    public void registrarMembresia(Membresia membresia) throws Exception {

        String sql = "INSERT INTO membresia (id_usuario, fecha_inicio, fecha_fin, costo, estado) VALUES (?, ?, ?, ?, ?)";

        DBHelper.manejarEntidad(sql, membresia.getCliente().getId(), Date.valueOf(membresia.getFechaInicio()), Date.valueOf(membresia.getFechaFin()), membresia.getCosto(), membresia.getEstado().toString());
    }

    @Override
    public void finalizarMembresia(int id_membresia) throws Exception {

        String sql = "UPDATE membresia SET estado = ?, fecha_fin = ? WHERE id_membresia = ?";

        DBHelper.manejarEntidad(sql, EstadoMembresia.finalizada.toString(), Date.valueOf(LocalDate.now()), id_membresia);
    }

    @Override
    public Membresia buscarMembresiaPorId(int id_membresia) throws Exception {

        String sql = "SELECT * FROM membresia WHERE id_membresia = ?";

        return DBHelper.obtenerEntidad(sql, this::mapearMembresia, id_membresia);
    }

    @Override
    public List<Membresia> verMembresiasPorUsuario(int id_usuario) throws Exception {

        String sql = "SELECT * FROM membresia WHERE id_usuario = ? ORDER BY fecha_inicio DESC";

        return DBHelper.obtenerListaEntidad(sql, this::mapearMembresia, id_usuario);
    }

    @Override
    public List<Membresia> verMembresias() throws Exception {

        String sql = "SELECT * FROM membresia ORDER BY fecha_inicio DESC";

        return DBHelper.obtenerListaEntidad(sql, this::mapearMembresia);
    }

    @Override
    public void actualizarMembresia(Membresia membresia) throws Exception {

        LocalDate nuevaFechaFin = membresia.getFechaFin().plusDays(30);

        String sql = "UPDATE membresia SET fecha_fin = ? WHERE id_membresia = ?";

        DBHelper.manejarEntidad(sql, Date.valueOf(nuevaFechaFin), membresia.getId());

        membresia.setFechaFin(nuevaFechaFin);
    }

    @Override
    public boolean tieneMembresiaActiva(int id_usuario) throws Exception {

        String sql = "SELECT COUNT(*) FROM membresia WHERE id_usuario = ? AND estado = ?";

        Integer tiene_membresia = DBHelper.obtenerEntidad(sql, rs -> 1, id_usuario, EstadoMembresia.activa.toString());

        return tiene_membresia !=null;
    }

    private Membresia mapearMembresia(ResultSet rs) throws Exception{

        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();

        int id_usuario = rs.getInt("id_usuario");

        Cliente cliente = (Cliente) usuarioDAO.buscarUsuarioPorId(id_usuario);

        return new Membresia(rs.getInt("id_membresia"), cliente, rs.getDate("fecha_inicio").toLocalDate(), rs.getDate("fecha_fin").toLocalDate(), rs.getInt("costo"), EstadoMembresia.valueOf(rs.getString("estado")));
    }
}
