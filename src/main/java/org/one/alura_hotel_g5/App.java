package org.one.alura_hotel_g5;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
		Scene scene = new Scene(loadFXML("mainWindow"));
		stage.setScene(scene);
		stage.getIcons().clear();
		stage.getIcons().add(new Image(App.class.getResourceAsStream("/images/aH-40px.png")));
		stage.setTitle("Alura Hotel");
		stage.setResizable(false);
		stage.show();
		stage.centerOnScreen();

	}

	public static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}

}