package org.one.alura_hotel_g5;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	@Override
	public void start(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("mainWindow.fxml")));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.getIcons().clear();
		stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("/images/aH-40px.png"))));
		stage.setTitle("Alura Hotel");
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}

	public static void loadView(String fxml, Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.getIcons().clear();
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}

	public static void salir(Stage stage, Event event) throws IOException {
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		loadView("loginWindow", stage);
		stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("/images/aH-40px.png"))));
	}

	public static void main(String[] args) {
		launch();
	}

}