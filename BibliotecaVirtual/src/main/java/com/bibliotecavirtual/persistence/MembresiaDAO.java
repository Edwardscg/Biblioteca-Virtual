package com.bibliotecavirtual.persistence;

import com.bibliotecavirtual.model.Membresia;

import java.util.List;

public interface MembresiaDAO {

    void registrarMembresia(Membresia membresia) throws Exception;

    void finalizarMembresia(int id_membresia) throws Exception;

    Membresia buscarMembresiaPorId(int id_membresia) throws Exception;

    List<Membresia> verMembresiasPorUsuario(int id_usuario) throws Exception;

    List<Membresia> verMembresias() throws Exception;

    void actualizarMembresia(Membresia membresia) throws Exception;

    boolean tieneMembresiaActiva(int id_usuario) throws Exception;
}
