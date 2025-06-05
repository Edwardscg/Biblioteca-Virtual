package com.bibliotecavirtual.persistence;

import com.bibliotecavirtual.model.Cliente;

public interface ClienteDAO {

    void actualizarCliente(Cliente cliente) throws Exception;

    Cliente obtenerCliente(int id_usuario) throws Exception;

}
