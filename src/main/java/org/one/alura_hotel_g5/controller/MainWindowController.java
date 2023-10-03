package org.one.alura_hotel_g5.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.one.alura_hotel_g5.App;

import java.io.IOException;
import java.util.Objects;

@SuppressWarnings("DuplicatedCode")
public class MainWindowController {
	private Stage stage;

	@FXML
	private VBox loginVBox;

	@FXML
	protected void login() {
		loginVBox.setOnMouseClicked(event -> {
			try {
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				App.loadView("loginWindow", stage);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("/images/aH-40px.png"))));
		});
	}
}
