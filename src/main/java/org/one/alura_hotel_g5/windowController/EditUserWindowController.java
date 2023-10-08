package org.one.alura_hotel_g5.windowController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.one.alura_hotel_g5.App;

import java.io.IOException;

public class EditUserWindowController {

    private Stage stage;

    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;
    @FXML private Button btnAnadir;
    @FXML private Button btnSalir;

    @FXML
    protected void exit() {
        btnSalir.setOnAction(event -> {
            try {
                App.salir(stage, event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
