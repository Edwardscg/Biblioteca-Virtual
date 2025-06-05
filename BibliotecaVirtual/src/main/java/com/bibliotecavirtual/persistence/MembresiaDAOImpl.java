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

        DBHelper.manejarEntidad(sql, membresia.getCliente().getId(), membresia.getFechaInicio(), membresia.getFechaFin(), membresia.getCosto(), membresia.getEstado());
    }

    @Override
    public void finalizarMembresia(int id_membresia) throws Exception {

        String sql = "UPDATE membresia SET estado = ?, fecha_fin = ? WHERE id_membresia = ?";

        LocalDate fecha_actual = LocalDate.now();

        DBHelper.manejarEntidad(sql, EstadoMembresia.finalizada.toString(), Date.valueOf(fecha_actual), id_membresia);
    }

    @Override
    public Membresia buscarMembresiaPorId(int id_membresia) throws Exception {

        String sql = "SELECT * FROM membresia WHERE id_membresia = ?";

        ResultSet rs = DBHelper.ejecutarConsulta(sql, id_membresia);

        if(rs.next()){

            return mapearMembresia(rs);
        }
        return null;
    }

    @Override
    public List<Membresia> verMembresiasPorUsuario(int id_usuario) throws Exception {

        List<Membresia> menbresias = new ArrayList<>();

        String sql = "SELECT * FROM membresia WHERE id_usuario = ? ORDER BY fecha_inicio DESC";

        ResultSet rs = DBHelper.ejecutarConsulta(sql, id_usuario);

        while (rs.next()){

            menbresias.add(mapearMembresia(rs));
        }
        return menbresias;
    }

    @Override
    public List<Membresia> verMembresias() throws Exception {

        List<Membresia> membresias = new ArrayList<>();

        String sql = "SELECT * FROM membresia ORDER BY fecha_inicio DESC";

        ResultSet rs = DBHelper.ejecutarConsulta(sql);

        while (rs.next()){

            membresias.add(mapearMembresia(rs));
        }
        return membresias;
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

        ResultSet rs = DBHelper.ejecutarConsulta(sql, id_usuario, EstadoMembresia.activa.toString());

        if(rs.next()){

            return rs.getInt(1) == 1;
        }

        return false;
    }

    private Cliente buscarClientePorId(int id_usuario) throws Exception{

        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        return (Cliente) usuarioDAO.buscarUsuarioPorId(id_usuario);
    }

    private Membresia mapearMembresia(ResultSet rs) throws Exception{
        int id_usuario = rs.getInt("id_usuario");
        Cliente cliente = buscarClientePorId(id_usuario);
        return new Membresia(rs.getInt("id_membresia"), cliente, rs.getDate("fecha_inicio").toLocalDate(), rs.getDate("fecha_fin").toLocalDate(), rs.getInt("costo"), EstadoMembresia.valueOf(rs.getString("estado")));
    }
}
