package com.bibliotecavirtual.logic;

import com.bibliotecavirtual.model.EstadoMembresia;
import com.bibliotecavirtual.model.Membresia;
import com.bibliotecavirtual.persistence.MembresiaDAOImpl;
import java.time.LocalDate;
import java.util.List;

public class MembresiaService {

    private final MembresiaDAOImpl membresiaDAO;

    public MembresiaService() {
        this.membresiaDAO = new MembresiaDAOImpl();
    }

    public void registrarMembresia(Membresia membresia) throws Exception{

        validarMembresia(membresia, false);

        membresiaDAO.registrarMembresia(membresia);
    }

    public void actualizarMembresia (Membresia membresia) throws Exception{

        validarMembresia(membresia, true);

        membresiaDAO.actualizarMembresia(membresia);
    }

    private void finalizarMembresia(int id_membresia) throws Exception{

        if(id_membresia <=0){

            throw new IllegalArgumentException("ID de membresía inválido");
        }

        membresiaDAO.finalizarMembresia(id_membresia);
    }

    public Membresia buscarMembresiaPorId(int id_membresia) throws Exception{

        if(id_membresia <=0){

            throw new IllegalArgumentException("ID de membresía inválido");
        }
        return membresiaDAO.buscarMembresiaPorId(id_membresia);
    }

    public List<Membresia> verMembresiasPorUsuario(int id_usuario) throws Exception{

        if(id_usuario <=0){

            throw new IllegalArgumentException("ID de usuario inválido");
        }

        return membresiaDAO.verMembresiasPorUsuario(id_usuario);
    }

    public List<Membresia> verMembresias() throws Exception{

        return membresiaDAO.verMembresias();
    }

    private void validarMembresia(Membresia membresia, boolean requiere_id) throws Exception{

        if (membresia == null) {
            throw new IllegalArgumentException("La membresía no puede ser nula.");
        }

        if (requiere_id && membresia.getId()<= 0) {
            throw new IllegalArgumentException("ID de membresía inválido.");
        }

        if (membresia.getCliente().getId() <= 0) {
            throw new IllegalArgumentException("ID de usuario inválido.");
        }

        if (membresia.getFechaFin() == null || membresia.getFechaFin().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de fin debe ser posterior a hoy.");
        }

        if (membresia.getCosto() < 0) {
            throw new IllegalArgumentException("El costo no puede ser negativo.");
        }

        EstadoMembresia estado = membresia.getEstado();
        if ((estado != EstadoMembresia.activa && estado != EstadoMembresia.finalizada)) {
            throw new IllegalArgumentException("Estado inválido. Debe ser 'activa' o 'finalizada'.");
        }
    }
}
