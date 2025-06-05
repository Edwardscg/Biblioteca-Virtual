package com.bibliotecavirtual.persistence;

import com.bibliotecavirtual.model.Valoracion;

import java.util.List;

public interface ValoracionDAO {

    void registrarValoracion(Valoracion valoracion) throws Exception;

    void actualizarValoracion(Valoracion valoracion) throws Exception;

    void eliminarValoracion(int id_usuario, int id_libro) throws Exception;

    Valoracion obtenerValarionPorUsuarioYLibro(int id_usuario, int id_libro) throws Exception;

    float obtenerPromedioValoracion(int id_libro) throws Exception;

    List<Valoracion> verValoracionesPorLibro(int id_libro) throws Exception;

    boolean haValorado(int id_usuario, int id_libro) throws Exception;

}
