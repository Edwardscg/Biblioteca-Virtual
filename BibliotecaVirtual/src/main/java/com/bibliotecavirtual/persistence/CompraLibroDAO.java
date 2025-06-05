package com.bibliotecavirtual.persistence;

import com.bibliotecavirtual.model.CompraLibro;
import java.util.List;

public interface CompraLibroDAO {

    void registrarCompra(CompraLibro compra) throws Exception;

    boolean estaComprado(int id_usuario, int id_libro) throws Exception;

    List<CompraLibro> verComprasPorUsuario(int id_usuario) throws Exception;

    List<CompraLibro> verCompras() throws Exception;
}
