package org.one.alura_hotel_g5.controller;

import org.one.alura_hotel_g5.dao.HuespedesDAO;
import org.one.alura_hotel_g5.factory.ConnectionFactory;
import org.one.alura_hotel_g5.model.Huespedes;

import java.util.List;

public class HuespedesController {

    private final HuespedesDAO huespedesDAO;
    private final ConnectionFactory connectionFactory;

    public HuespedesController() {
        connectionFactory = new ConnectionFactory();
        this.huespedesDAO = new HuespedesDAO(connectionFactory.recuperarConexion());
    }

    public int guardar(Huespedes huesped) {
        return huespedesDAO.guardar(huesped);
    }

    public int eliminar(Integer reserva_id) {
        return huespedesDAO.eliminar(reserva_id);
    }

    public boolean modificar(Integer id, String nacionalidad, String telefono) {
        return huespedesDAO.modificar(id, nacionalidad, telefono);
    }

    public List<Huespedes> listarTodos() {
        return huespedesDAO.listarTodos();
    }

    public List<Huespedes> buscarPorParametro(Object parametro) {
        return huespedesDAO.buscarPorParametro(parametro);
    }
}
