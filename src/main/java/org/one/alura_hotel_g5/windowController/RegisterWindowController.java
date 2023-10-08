package org.one.alura_hotel_g5.windowController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.one.alura_hotel_g5.App;
import org.one.alura_hotel_g5.model.FormaPago;

import java.io.IOException;
import java.time.LocalDate;

@SuppressWarnings("DuplicatedCode")
public class RegisterWindowController {

    private Stage stage;
    private double costo;

    @FXML private TabPane ventanaReservas;
    @FXML private TextArea txtNombre;
    @FXML private TextArea txtApellido;
    @FXML private ComboBox<String> cbNacionalidad;
    @FXML private TextArea txtTelefono;
    @FXML private Button btnSiguienteH;
    @FXML private Button btnAtrasH;
    @FXML private DatePicker dpNacimiento;
    @FXML private Label lblReserva;
    @FXML private DatePicker dpCheckin;
    @FXML private DatePicker dpCheckout;
    @FXML private Label lblCosto;
    @FXML private ComboBox<FormaPago> cbFormaPago;
    @FXML private Button btnSiguiente;
    @FXML private Button btnAtras;
    @FXML private Button btnCalcular;

    @FXML
    protected void exit() {
        var tab = ventanaReservas.getSelectionModel().getSelectedItem();
        if (tab.getId().equals("reservaTab")) {
            btnAtras.setOnAction(event -> {
                try {
                    App.salir(stage, event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } else {
            btnAtrasH.setOnAction(event -> ventanaReservas.getSelectionModel().selectPrevious());
        }
    }

    @FXML
    protected void calcularCosto() {
        btnCalcular.setOnAction(event -> {
            costo = 0.00;
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
    void siguiente() {
        var tab = ventanaReservas.getSelectionModel().getSelectedItem();
        if (tab.getId().equals("reservaTab")) {
            btnSiguiente.setOnAction(event -> ventanaReservas.getSelectionModel().selectNext());
        } else {
            System.out.println(ventanaReservas.getSelectionModel().getSelectedItem().getId());
        }
    }

    @FXML
    void initialize() {
        ObservableList<FormaPago> formasPago = FXCollections.observableArrayList();
        ObservableList<String> nacionalidades = FXCollections.observableArrayList();
        formasPago.addAll(FormaPago.values());
        nacionalidades.addAll("afgana", "alemana", "árabe", "argentina", "australiana", "belga", "boliviana",
                "brasileña", "camboyana", "canadiense", "chilena", "china", "colombiana", "coreana", "costarricense",
                "cubana", "danesa", "ecuatoriana", "egipcia", "salvadoreña", "escocesa", "española", "estadounidense",
                "estonia", "etiope", "filipina", "finlandesa", "francesa", "galesa", "griega", "guatemalteca",
                "haitiana", "holandesa", "hondureña", "indonesa", "inglesa", "iraquí", "iraní", "irlandesa", "israelí",
                "italiana", "japonesa", "jordana", "laosiana", "letonesa", "malaya", "marroquí", "mexicana",
                "nicaragüense", "noruega", "neozelandesa", "panameña", "paraguaya", "peruana", "polaca", "portuguesa",
                "puertorriqueño", "dominicana", "rumana", "rusa", "sueca", "suiza", "tailandesa", "taiwanesa", "turca",
                "ucraniana", "uruguaya", "venezolana", "vietnamita");
        Callback<DatePicker, DateCell> reservaDayFactory = dp -> new DateCell() {
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
        Callback<DatePicker, DateCell> nacimientoDayFactory = dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                this.setDisable(false);
                this.setBackground(null);
                if (item.isAfter(LocalDate.now().minusYears(18))) {
                    this.setDisable(true);
                }
            }
        };

        cbFormaPago.setItems(formasPago);
        cbNacionalidad.setItems(nacionalidades);
        dpNacimiento.setDayCellFactory(nacimientoDayFactory);
        dpCheckin.setDayCellFactory(reservaDayFactory);
        dpCheckout.setDayCellFactory(reservaDayFactory);
        siguiente();
    }
}
