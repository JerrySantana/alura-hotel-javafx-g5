package org.one.alura_hotel_g5.dao;

import org.one.alura_hotel_g5.model.Huespedes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HuespedesDAO {

    final private Connection connection;

    public HuespedesDAO(Connection connection) {
        this.connection = connection;
    }

    public Integer guardar(Huespedes huesped) {
        try {
            final PreparedStatement statement = connection.prepareStatement(
                    "insert into huespedes " + "(nombre, apellido, fechaNacimiento, nacionalidad, telefono, reserva_id)"
                    + " values(?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            try (statement) {
                statement.setString(1, huesped.getNombre());
                statement.setString(2, huesped.getApellido());
                statement.setString(3, huesped.getFechaNacimiento().toString());
                statement.setString(4, huesped.getNacionalidad());
                statement.setString(5, huesped.getTelefono());
                statement.setInt(6, huesped.getIdReserva());

                statement.execute();

                final ResultSet resultSet =statement.getGeneratedKeys();

                try (resultSet) {
                    while (resultSet.next()) {
                        huesped.setId(resultSet.getInt(1));
                    }
                }
                return huesped.getId();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer eliminar(Integer reserva_id) {
        try {
            final PreparedStatement statement = connection
                    .prepareStatement("delete from huespedes where reserva_id = ?");

            try (statement) {
                statement.setInt(1, reserva_id);

                statement.execute();

                return statement.getUpdateCount();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean modificar(Integer id, String nacionalidad, String telefono) {
        try {
            final PreparedStatement statement = connection
                    .prepareStatement("update huespedes set " + "nacionalidad = ?" + ", telefono = ?" + " where id = ?");

            try (statement) {
                statement.setString(1, nacionalidad);
                statement.setString(2, telefono);
                statement.setInt(3, id);

                statement.execute();
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Huespedes> listarTodos() {
        List<Huespedes> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = connection.prepareStatement(
                    "select id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, reserva_id from huespedes");

            try (statement) {
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                return getHuespedes(resultado, resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Huespedes> buscarPorParametro(Object parametro) {
        if (parametro == null) {
            return null;
        }
        List<Huespedes> resultado = new ArrayList<>();
        try {
            var string = "";
            if (parametro.getClass() == String.class) {
                string = "apellido = ?";
            }
            if (parametro.getClass() == Integer.class) {
                string = "id = ?";
            }
            String query = "select id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, reserva_id from huespedes where "
                    + string;

            final PreparedStatement statement = connection.prepareStatement(query);

            try (statement) {
                final ResultSet resultSet;
                if (parametro.getClass() == String.class) {
                    statement.setString(1, String.valueOf(parametro));
                } else if (parametro.getClass() == Integer.class) {
                    statement.setInt(1, (Integer) parametro);
                }
                statement.execute();

                resultSet = statement.getResultSet();

                return getHuespedes(resultado, resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Huespedes> getHuespedes(List<Huespedes> resultado, ResultSet resultSet) throws SQLException {
        try (resultSet) {
            while (resultSet.next()) {
                Huespedes fila = new Huespedes(resultSet.getInt("id"), resultSet.getString("nombre"),
                        resultSet.getString("apellido"), resultSet.getDate("fechaNacimiento"),
                        resultSet.getString("nacionalidad"), resultSet.getString("telefono"),
                        resultSet.getInt("reserva_id"));

                resultado.add(fila);
            }

            return resultado;
        }
    }
}
