package org.one.alura_hotel_g5.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.one.alura_hotel_g5.App;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

@SuppressWarnings("DuplicatedCode")
public class RegisterWindowController {

    private Stage stage;

    @FXML private DatePicker dpCheckin;
    @FXML private DatePicker dpCheckout;
    @FXML private Label lblCosto;
    @FXML private ComboBox<String> cbFormaPago;
    @FXML private Button btnSiguiente;
    @FXML private Button btnAtras;
    @FXML private Button btnCalcular;

    @FXML
    protected void exit() {
        btnAtras.setOnAction(event -> {
            try {
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                App.loadView("loginWindow", stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("/images/aH-40px.png"))));
        });
    }

    @FXML
    protected void calcularCosto() {
        btnCalcular.setOnAction(event -> {
            var costo = 0.00;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            if (LocalDate.from(dpCheckout.getValue()).isBefore(LocalDate.from(dpCheckin.getValue()))) {
                lblCosto.setText("$" + costo + " (MXN)");
                alert.setTitle("Date error");
                alert.setHeaderText("La fecha de check-out debe ser mayor a la fecha de check-in.");
                alert.showAndWait();
            } else if (LocalDate.from(dpCheckout.getValue()).equals(LocalDate.from(dpCheckin.getValue()))) {
                lblCosto.setText("$" + costo + " (MXN)");
                alert.setTitle("Date error");
                alert.setHeaderText("La fecha de check-out debe ser mayor a la fecha de check-in.");
                alert.showAndWait();
            } else if (LocalDate.from(dpCheckout.getValue()).isAfter(LocalDate.from(dpCheckin.getValue()))) {
                var dias = LocalDate.from(dpCheckin.getValue()).until(LocalDate.from(dpCheckout.getValue())).getDays();
                costo = dias * 200;
                lblCosto.setText("$" + costo + " (MXN)");
            }
        });
    }

    @FXML
    void initialize() {
        ObservableList<String> formasPago = FXCollections.observableArrayList();
        formasPago.addAll("Tarjeta de crédito", "Tarjeta de débito", "Efectivo");
        cbFormaPago.setItems(formasPago);
        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                this.setDisable(false);
                this.setBackground(null);
                if (item.isBefore(LocalDate.now())) {
                    this.setDisable(true);
                }
                if (item.isAfter(LocalDate.now().plusMonths(6))) {
                    this.setDisable(true);
                }
            }
        };

        dpCheckin.setDayCellFactory(dayCellFactory);
        dpCheckout.setDayCellFactory(dayCellFactory);
    }
}
