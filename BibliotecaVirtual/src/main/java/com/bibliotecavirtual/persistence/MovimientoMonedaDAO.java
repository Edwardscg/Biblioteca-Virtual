package com.bibliotecavirtual.persistence;

import com.bibliotecavirtual.model.MovimientoMoneda;

import java.util.List;

public interface MovimientoMonedaDAO {

    void registrarMovimiento(MovimientoMoneda movimiviento) throws Exception;

    List<MovimientoMoneda> verMovimientosPorUsuario(int id_usuario) throws Exception;

    List<MovimientoMoneda> verMovimientos() throws Exception;

}
