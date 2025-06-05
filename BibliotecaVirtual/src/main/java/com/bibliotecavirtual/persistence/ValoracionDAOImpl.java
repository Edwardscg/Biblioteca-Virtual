package com.bibliotecavirtual.persistence;

import com.bibliotecavirtual.logic.UsuarioService;
import com.bibliotecavirtual.model.Cliente;
import com.bibliotecavirtual.model.Libro;
import com.bibliotecavirtual.model.Usuario;
import com.bibliotecavirtual.model.Valoracion;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ValoracionDAOImpl implements ValoracionDAO{

    @Override
    public void registrarValoracion(Valoracion valoracion) throws Exception {

        String sql = "INSERT INTO valoracion (id_usuario, id_libro, puntuacion, comentario, fecha_valoracion) VALUES (?, ?, ?, ?, ?);";

        DBHelper.manejarEntidad(sql, valoracion.getCliente().getId(), valoracion.getLibro().getId(), valoracion.getPuntuacion(), valoracion.getComentario(), Date.valueOf(valoracion.getFecha_valoracion()));
    }

    @Override
    public void actualizarValoracion(Valoracion valoracion) throws Exception {

        String sql = "UPDATE valoracion SET puntuacion = ?, comentario = ?, fecha_valoracion = ? WHERE id_usuario = ? AND id_libro = ?;";

        DBHelper.manejarEntidad(sql, valoracion.getPuntuacion(), valoracion.getComentario(), Date.valueOf(valoracion.getFecha_valoracion()), valoracion.getCliente().getId(), valoracion.getLibro().getId());
    }

    @Override
    public void eliminarValoracion(int id_usuario, int id_libro) throws Exception {
        String sql = "DELETE FROM valoracion WHERE id_usuario = ? AND id_libro = ?;";

        DBHelper.manejarEntidad(sql, id_usuario, id_libro);
    }

    @Override
    public Valoracion obtenerValarionPorUsuarioYLibro(int id_usuario, int id_libro) throws Exception {

        String sql = "SELECT * FROM valoracion WHERE id_usuario = ? AND id_libro = ?;";

        ResultSet rs = DBHelper.ejecutarConsulta(sql, id_usuario, id_libro);

        if(rs.next()){

            return mapearValoracion(rs);
        }

        return null;
    }

    @Override
    public float obtenerPromedioValoracion(int id_libro) throws Exception {

        String sql = "SELECT AVG(puntuacion) AS promedio FROM valoracion WHERE id_libro = ?;";

        ResultSet rs = DBHelper.ejecutarConsulta(sql, id_libro);

        if(rs.next()){

            return rs.getFloat("promedio");
        }
        return 0;
    }

    @Override
    public List<Valoracion> verValoracionesPorLibro(int id_libro) throws Exception {

        List<Valoracion> valoraciones = new ArrayList<>();

        String sql = "SELECT * FROM valoracion WHERE id_libro = ? ORDER BY fecha DESC;";

        ResultSet rs = DBHelper.ejecutarConsulta(sql, id_libro);

        while (rs.next()){

            valoraciones.add(mapearValoracion(rs));
        }
        return valoraciones;
    }

    @Override
    public boolean haValorado(int id_usuario, int id_libro) throws Exception {

        String sql = "SELECT COUNT(*) AS total FROM valoracion WHERE id_usuario = ? AND id_libro = ?;";

        ResultSet rs = DBHelper.ejecutarConsulta(sql, id_usuario, id_libro);

        if (rs.next()){

            return rs.getInt("total")>0;
        }
        return false;
    }

    private Valoracion mapearValoracion(ResultSet rs) throws Exception{

        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        LibroDAOImpl libroDAO = new LibroDAOImpl();

        Usuario usuario = usuarioDAO.buscarUsuarioPorId(rs.getInt("id_usuario"));
        Libro libro = libroDAO.buscarLibroPorId(rs.getInt("id_libro"));

        return new Valoracion(rs.getInt("id_valoracion"), (Cliente) usuario, libro, rs.getInt("puntuacion"), rs.getString("comentario"), rs.getDate("fecha_valoracion").toLocalDate());
    }
}
