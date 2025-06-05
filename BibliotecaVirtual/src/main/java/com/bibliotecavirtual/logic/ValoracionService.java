package com.bibliotecavirtual.logic;

import com.bibliotecavirtual.model.Valoracion;
import com.bibliotecavirtual.persistence.LibroDAOImpl;
import com.bibliotecavirtual.persistence.ValoracionDAO;
import com.bibliotecavirtual.persistence.ValoracionDAOImpl;

import java.util.List;

public class ValoracionService {

    private ValoracionDAOImpl valoracionDAO;

    public ValoracionService(){

        this.valoracionDAO = new ValoracionDAOImpl();
    }

    public void registrarValoracion(Valoracion valoracion) throws Exception{

        valoracionDAO.registrarValoracion(valoracion);
    }

    public void actualizarValoracion(Valoracion valoracion) throws Exception{

        valoracionDAO.actualizarValoracion(valoracion);
    }

    public void eliminarValoracion(int id_usuario, int id_libro) throws Exception{

        valoracionDAO.eliminarValoracion(id_usuario, id_libro);

        float nuevo_promedio = valoracionDAO.obtenerPromedioValoracion(id_libro);
        List<Valoracion> valoraciones = valoracionDAO.verValoracionesPorLibro(id_libro);
        int nueva_cantidad = valoraciones.size();

        LibroDAOImpl libroDAO = new LibroDAOImpl();
        libroDAO.actualizarPuntuacionYValoracion(id_libro, nuevo_promedio, nueva_cantidad);
    }

    public Valoracion obtenerValoracionPorUsuarioYLibro(int id_usuario, int id_libro) throws Exception{

        return valoracionDAO.obtenerValarionPorUsuarioYLibro(id_usuario, id_libro);
    }

    public float obtenerPromedioValoracion(int id_libro) throws Exception{

        return valoracionDAO.obtenerPromedioValoracion(id_libro);
    }

    public List<Valoracion> verValoracionesPorLibro(int id_libro) throws Exception{

        return valoracionDAO.verValoracionesPorLibro(id_libro);
    }

    public boolean haValorado(int id_usuario, int id_libro) throws Exception{

        return valoracionDAO.haValorado(id_usuario, id_libro);
    }
}
