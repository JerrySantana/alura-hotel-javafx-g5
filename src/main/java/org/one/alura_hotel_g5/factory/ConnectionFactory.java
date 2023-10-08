package org.one.alura_hotel_g5.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;
import javafx.scene.control.Alert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    private final DataSource dataSource;

    public ConnectionFactory() {
        ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
        try {
            Dotenv dotenv = Dotenv.configure().directory("src/main/java/org/one/alura_hotel_g5/security/.env").load();
            pooledDataSource.setJdbcUrl(dotenv.get("db.url"));
            pooledDataSource.setUser(dotenv.get("db.user"));
            pooledDataSource.setPassword(dotenv.get("db.password"));
            pooledDataSource.setMaxPoolSize(10);

            this.dataSource = pooledDataSource;
        } catch (DotenvException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Data error");
            alert.setHeaderText("Hubo un error al intentar acceder a los datos.");
            alert.showAndWait();
            throw new RuntimeException(e);
        }
    }

    public Connection recuperarConexion() {
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
