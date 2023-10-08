package org.one.alura_hotel_g5.windowController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.one.alura_hotel_g5.App;
import org.one.alura_hotel_g5.controller.HuespedesController;
import org.one.alura_hotel_g5.controller.ReservasController;
import org.one.alura_hotel_g5.model.FormaPago;
import org.one.alura_hotel_g5.model.Habitacion;
import org.one.alura_hotel_g5.model.Huespedes;
import org.one.alura_hotel_g5.model.Reservas;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@SuppressWarnings("DuplicatedCode")
public class RegisterWindowController {

    protected Stage stage;
    private double costo;
    public static int reservaId;
    private Alert alert;
    private ReservasController reservasController;
    private HuespedesController huespedesController;

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
    @FXML private ComboBox<Habitacion> cbTipoHabitacion;
    @FXML private ComboBox<Integer> cbNumeroHabitacion;
    @FXML private Button btnSiguiente;
    @FXML private Button btnAtras;
    @FXML private Button btnCalcular;

    @FXML
    protected void exit() {
        var tab = ventanaReservas.getSelectionModel().getSelectedItem();
        if (tab.getId().equals("reservaTab")) {
            btnAtras.setOnAction(event -> {
                try {
                    reservasController.eliminar(reservaId);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        App.salir(stage, event);
                    } catch (IOException e) {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setHeaderText(e.getMessage());
                        alert.showAndWait();
                    }
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
            alert = new Alert(Alert.AlertType.ERROR);
            if (compararFecha()) {
                var dias = dpCheckin.getValue().datesUntil(dpCheckout.getValue()).count();
                var habitacion = calcularHabitacion();
                costo = dias * habitacion;
                lblCosto.setText("$" + costo + " (MXN)");
            } else {
                alert.setTitle("Date error");
                alert.setHeaderText("La fecha de check-out debe ser mayor a la fecha de check-in.");
                alert.showAndWait();
            }
        });
    }

    @FXML
    void siguiente() {
        var tab = ventanaReservas.getSelectionModel().getSelectedItem();
        if (tab.getId().equals("reservaTab")) {
            reservar();
            btnSiguiente.setOnAction(event -> ventanaReservas.getSelectionModel().selectNext());
            lblReserva.setText(""+reservaId);
        } else {
            btnSiguienteH.setOnAction(event -> {
                guardarHuesped();
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText("Datos guardados correctamente.");
                alert.showAndWait();
                try {
                    App.salir(stage, event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    @FXML
    void initialize() {
        ObservableList<FormaPago> formasPago = FXCollections.observableArrayList();
        ObservableList<String> nacionalidades = FXCollections.observableArrayList();
        ObservableList<Habitacion> tipoHabitaciones = FXCollections.observableArrayList();
        ObservableList<Integer> habitaciones = FXCollections.observableArrayList();
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
        tipoHabitaciones.addAll(Habitacion.values());
        habitaciones.addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

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
        cbTipoHabitacion.setItems(tipoHabitaciones);
        cbNumeroHabitacion.setItems(habitaciones);
        dpNacimiento.setDayCellFactory(nacimientoDayFactory);
        dpCheckin.setDayCellFactory(reservaDayFactory);
        dpCheckout.setDayCellFactory(reservaDayFactory);
        reservasController = new ReservasController();
        huespedesController = new  HuespedesController();
    }

    private void reservar() {
        var checkIn = Date.valueOf(dpCheckin.getValue());
        var checkOut = Date.valueOf(dpCheckout.getValue());
        var formaPago = cbFormaPago.getValue();
        var habitacion = cbNumeroHabitacion.getSelectionModel().getSelectedItem();
        var tipoHabitacion = cbTipoHabitacion.getSelectionModel().getSelectedItem();
        Reservas reserva = new Reservas(checkIn, checkOut, costo, formaPago, habitacion, tipoHabitacion);
        reservaId = reservasController.guardar(reserva);
    }

    private void guardarHuesped() {
        var nombre = txtNombre.getText();
        var apellido = txtApellido.getText();
        var fechaNacimiento = Date.valueOf(dpNacimiento.getValue());
        var nacionalidad = cbNacionalidad.getSelectionModel().getSelectedItem();
        var telefono = txtTelefono.getText();
        Huespedes huesped = new Huespedes(nombre, apellido, fechaNacimiento, nacionalidad, telefono);
        huesped.setIdReserva(reservaId);
        huespedesController.guardar(huesped);
    }

    protected boolean compararFecha() {
        return LocalDate.from(dpCheckout.getValue()).isAfter(LocalDate.from(dpCheckin.getValue()));
    }

    protected int calcularHabitacion() {
        if (cbTipoHabitacion.getSelectionModel().getSelectedItem().equals(Habitacion.Doble)) {
            return 300;
        }
        if (cbTipoHabitacion.getSelectionModel().getSelectedItem().equals(Habitacion.Especial)) {
            return 660;
        }
        return 200;
    }

}
