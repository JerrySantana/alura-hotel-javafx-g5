package org.one.alura_hotel_g5.windowController;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.one.alura_hotel_g5.App;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

@SuppressWarnings("DuplicatedCode")
public class UserWindowController {

    private Stage stage;

    @FXML private VBox btnLogout;
    @FXML private HBox hbRegister;
    @FXML private HBox hbSearch;
    @FXML private Label lblFecha;

    @FXML
    protected void logOut() {
        btnLogout.setOnMouseClicked((EventHandler<Event>) event -> {
            try {
                App.salir(stage, event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    protected void makeReservation() {
        hbRegister.setOnMouseClicked((EventHandler<Event>) event -> {
            try {
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                App.loadView("registerWindow", stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("/images/calendario.png"))));
        });
    }

    @FXML
    protected void search() {
        hbSearch.setOnMouseClicked((EventHandler<Event>) event -> {
            try {
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                App.loadView("searchWindow", stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("/images/lupa2.png"))));
        });
    }

    @FXML
    void initialize() {
        var fecha = LocalDate.now().getDayOfMonth() + "/" + LocalDate.now().getMonth().getValue() + "/" + LocalDate.now().getYear();
        lblFecha.setText("La fecha es: " + fecha);
    }
}
