package org.one.alura_hotel_g5.dao;

import org.jetbrains.annotations.NotNull;
import org.one.alura_hotel_g5.model.FormaPago;
import org.one.alura_hotel_g5.model.Habitacion;
import org.one.alura_hotel_g5.model.Reservas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservasDAO {

    final private Connection connection;

    public ReservasDAO(Connection connection) {
        this.connection = connection;
    }

    public Integer guardar(@NotNull Reservas reserva) {
        try {
            final PreparedStatement statement = connection.prepareStatement(
                    "insert into reservas" + "(checkIn, checkOut, costo, formaPago, habitacion, tipoHabitacion)" + " values (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            try (statement) {
                statement.setString(1, reserva.getCheckIn().toString());
                statement.setString(2, reserva.getCheckOut().toString());
                statement.setDouble(3, reserva.getCosto());
                statement.setString(4, reserva.getFormaPago().name());
                statement.setInt(5, reserva.getNumeroHabitacion());
                statement.setString(6, reserva.getTipoHabitacion().name());

                statement.execute();

                final ResultSet resultSet = statement.getGeneratedKeys();

                try (resultSet) {
                    while (resultSet.next()) {
                        reserva.setId(resultSet.getInt(1));
                    }
                }
                return reserva.getId();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer eliminar(Integer id) {
        try {
            final PreparedStatement statement = connection.prepareStatement("delete from reservas where id = ?");

            try (statement) {
                statement.setInt(1, id);
                statement.execute();

                return statement.getUpdateCount();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean modificar(Integer id, Date checkOut, double costo, String formaPago, String tipoHabitacion) {
        try {
            final PreparedStatement statement = connection.prepareStatement(
                    "update reservas set " + "checkOut = ?" + ", costo = ?" + ", formaPago = ?" + ", tipoHabitacion = ?" + " where id = ?");

            try (statement) {
                statement.setString(1, checkOut.toString());
                statement.setDouble(2, costo);
                statement.setString(3, formaPago);
                statement.setString(4, tipoHabitacion);
                statement.setInt(5, id);

                statement.execute();
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Reservas> listarTodas() {
        List<Reservas> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = connection
                    .prepareStatement("select id, checkIn, checkOut, costo, formaPago, habitacion, tipoHabitacion from reservas");

            try (statement) {
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                return getReservas(resultado, resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Reservas> buscarPorParametro(Object parametro) {
        if (parametro == null) {
            return null;
        }
        List<Reservas> resultado = new ArrayList<>();
        try {
            var string = "";
            if (parametro.getClass() == Date.class) {
                string = "fechaEntrada = ? or fechaSalida = ?";
            }
            if (parametro.getClass() == Integer.class) {
                string = "id = ?";
            }
            String query = "select id, checkIn, checkOut, costo, formaPago, habitacion, tipoHabitacion from reservas where " + string;

            final PreparedStatement statement = connection.prepareStatement(query);

            try (statement) {
                final ResultSet resultSet;
                if (parametro.getClass() == Date.class) {
                    statement.setString(1, parametro.toString());
                    statement.setString(2, parametro.toString());
                } else if (parametro.getClass() == Integer.class) {
                    statement.setInt(1, (Integer) parametro);
                }
                statement.execute();

                resultSet = statement.getResultSet();

                return getReservas(resultado, resultSet);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Reservas> getReservas(List<Reservas> resultado, ResultSet resultSet) throws SQLException {
        try (resultSet) {
            while (resultSet.next()) {
                Reservas fila = new Reservas(resultSet.getInt("id"), resultSet.getDate("checkIn"),
                        resultSet.getDate("checkOut"), resultSet.getDouble("costo"),
                        FormaPago.valueOf(resultSet.getString("formaPago")), resultSet.getInt("habitacion"), Habitacion.valueOf(resultSet.getString("tipoHabitacion")));

                resultado.add(fila);
            }

            return resultado;
        }
    }
}
