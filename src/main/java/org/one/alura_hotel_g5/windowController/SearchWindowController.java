package org.one.alura_hotel_g5.windowController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.one.alura_hotel_g5.App;

import java.io.IOException;

public class SearchWindowController {

    private Stage stage;

    @FXML private TableView<Object> tableReservas; // Sustituir Object por Reservas
    @FXML private TableView<Object> tableHuespedes; // Sustituir Object por Huespedes
    @FXML private TextArea txtBuscarR;
    @FXML private TextArea txtBuscarH;
    @FXML private Button btnBuscarR;
    @FXML private Button btnBuscarH;
    @FXML private Button btnEditarR;
    @FXML private Button btnEditarH;
    @FXML private Button btnEliminarR;
    @FXML private Button btnEliminarH;
    @FXML private Button btnSalirR;
    @FXML private Button btnSalirH;

    @FXML
    protected void exit() {
        if(btnSalirR.isPressed()) {
            btnSalirR.setOnAction(event -> {
                try {
                    App.salir(stage, event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } else if (btnSalirH.isPressed()) {
            btnSalirH.setOnAction(event -> {
                try {
                    App.salir(stage, event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
