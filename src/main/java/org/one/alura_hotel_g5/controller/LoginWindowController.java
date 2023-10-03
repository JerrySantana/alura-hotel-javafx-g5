package org.one.alura_hotel_g5.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.one.alura_hotel_g5.App;

import java.io.IOException;
import java.util.Objects;

public class LoginWindowController {
	private Stage stage;

	@FXML private TextField txtUsuario;
	@FXML private PasswordField txtContrasena;
	@FXML private Button btnLogin;

	@FXML
	protected void login() {
		btnLogin.setOnAction(event -> {
            if (txtUsuario.getText().equals("admin") && txtContrasena.getText().equals("admin")) {
				try {
					stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					App.loadView("adminWindow", stage);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("/images/perfil-del-usuario.png"))));
            } else if (txtUsuario.getText().equals("user") && txtContrasena.getText().equals("user")) {
				try {
					stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					App.loadView("userWindow", stage);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("/images/perfil-del-usuario.png"))));
			}
        });
	}
}
