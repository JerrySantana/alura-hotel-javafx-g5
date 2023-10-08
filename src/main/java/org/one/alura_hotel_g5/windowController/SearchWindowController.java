package org.one.alura_hotel_g5.windowController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.one.alura_hotel_g5.App;
import org.one.alura_hotel_g5.controller.HuespedesController;
import org.one.alura_hotel_g5.controller.ReservasController;
import org.one.alura_hotel_g5.model.FormaPago;
import org.one.alura_hotel_g5.model.Habitacion;
import org.one.alura_hotel_g5.model.Huespedes;
import org.one.alura_hotel_g5.model.Reservas;

import java.io.IOException;
import java.sql.Date;

public class SearchWindowController {


    protected Stage stage;
    private ReservasController reservasController;
    private HuespedesController huespedesController;

    @FXML private TabPane ventanaBusqueda;
    @FXML private TableView<Reservas> tableReservas;
    @FXML private TableView<Huespedes> tableHuespedes;
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

    @FXML private TableColumn<Reservas, Integer> columnIdR;
    @FXML private TableColumn<Reservas, Date> columnCheckIn;
    @FXML private TableColumn<Reservas, Date> columnCheckOut;
    @FXML private TableColumn<Reservas, Double> columnCosto;
    @FXML private TableColumn<Reservas, FormaPago> columnFormaPago;
    @FXML private TableColumn<Reservas, Integer> columnHabitacion;
    @FXML private TableColumn<Reservas, Habitacion> columnTipoHabitacion;
    @FXML private TableColumn<Huespedes, Integer> columnIdH;
    @FXML private TableColumn<Huespedes, String> columnNombre;
    @FXML private TableColumn<Huespedes, String> columnApellido;
    @FXML private TableColumn<Huespedes, Date> columnNacimiento;
    @FXML private TableColumn<Huespedes, String> columnNacionalidad;
    @FXML private TableColumn<Huespedes, String> columnTelefono;
    @FXML private TableColumn<Huespedes, Integer> columnIdReserva;

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

    @FXML
    protected void initialize() {
        reservasController = new ReservasController();
        huespedesController = new HuespedesController();

        cellValues();

        ObservableList<Reservas> reservas = FXCollections.observableArrayList();
        ObservableList<Huespedes> huespedes = FXCollections.observableArrayList();

        reservas.addAll(reservasController.listarTodas());
        huespedes.addAll(huespedesController.listarTodos());

        tableReservas.setItems(reservas);
        tableHuespedes.setItems(huespedes);
    }

    private void cellValues() {
        columnIdR.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnCheckIn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
        columnCheckOut.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
        columnCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));
        columnFormaPago.setCellValueFactory(new PropertyValueFactory<>("formaPago"));
        columnHabitacion.setCellValueFactory(new PropertyValueFactory<>("numeroHabitacion"));
        columnTipoHabitacion.setCellValueFactory(new PropertyValueFactory<>("tipoHabitacion"));
        columnIdH.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        columnNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        columnNacionalidad.setCellValueFactory(new PropertyValueFactory<>("nacionalidad"));
        columnTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnIdReserva.setCellValueFactory(new PropertyValueFactory<>("idReserva"));
    }
}
