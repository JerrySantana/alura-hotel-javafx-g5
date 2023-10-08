package org.one.alura_hotel_g5.controller;

import org.one.alura_hotel_g5.dao.ReservasDAO;
import org.one.alura_hotel_g5.factory.ConnectionFactory;
import org.one.alura_hotel_g5.model.Habitacion;
import org.one.alura_hotel_g5.model.Reservas;

import java.sql.Date;
import java.util.List;

public class ReservasController {
    private ReservasDAO reservasDAO;
    private ConnectionFactory connectionFactory;

    public ReservasController() {
        connectionFactory = new ConnectionFactory();
        this.reservasDAO = new ReservasDAO(connectionFactory.recuperarConexion());
    }

    public int guardar(Reservas reserva) {
        return reservasDAO.guardar(reserva);
    }

    public int eliminar(Integer id) {
        return reservasDAO.eliminar(id);
    }

    public boolean modificar(Integer id, Date checkOut, double costo, String formaPago, String tipoHabitacion) {
        return reservasDAO.modificar(id, checkOut, costo, formaPago, tipoHabitacion);
    }

    public List<Reservas> listarTodas() {
        return reservasDAO.listarTodas();
    }

    public List<Reservas> buscarPorParametro(Object parametro) {
        return reservasDAO.buscarPorParametro(parametro);
    }
}
