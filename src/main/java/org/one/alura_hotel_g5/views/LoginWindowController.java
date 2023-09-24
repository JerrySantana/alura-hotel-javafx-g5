package org.one.alura_hotel_g5.views;

import java.io.IOException;

import org.one.alura_hotel_g5.App;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginWindowController {
	private Stage stage;
	private Scene scene;
	private AdminWindowController adminWindow;

	@FXML private TextField txtUsuario;
	@FXML private PasswordField txtContrasena;
	@FXML private Button btnLogin;

	public void LoginWindow(Event event) throws IOException {
		Parent root = App.loadFXML("loginWindow");
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.getIcons().clear();
		stage.getIcons().add(new Image(App.class.getResourceAsStream("/images/aH-40px.png")));
		stage.show();
		stage.setResizable(false);
		stage.centerOnScreen();
	}

	@FXML
	protected void login() {
		btnLogin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				adminWindow = new AdminWindowController();
				if (txtUsuario.getText().equals("admin") && txtContrasena.getText().equals("admin")) {
					try {
						adminWindow.AdminWindow(event);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}
}
