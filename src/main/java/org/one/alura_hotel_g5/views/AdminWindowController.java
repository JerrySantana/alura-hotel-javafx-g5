package org.one.alura_hotel_g5.views;

import java.io.IOException;
import java.time.LocalDate;

import org.one.alura_hotel_g5.App;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AdminWindowController {
	private Stage stage;
	private Scene scene;
	private LoginWindowController parent;
	
	@FXML private ImageView btnLogout;
	@FXML private HBox hbRegister;
	@FXML private HBox hbSearch;
	@FXML private Label lblFecha;
	@FXML private Label lblInfo;
	
	@FXML
	public void AdminWindow(Event event) throws IOException{
		Parent root = App.loadFXML("adminWindow");
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		LocalDate fecha = LocalDate.now();
		System.out.println(root.getChildrenUnmodifiable().toString());
		stage.setScene(scene);
		stage.show();
		stage.getIcons().clear();
		stage.getIcons().add(new Image(App.class.getResourceAsStream("/images/perfil-del-usuario.png")));
		stage.setResizable(false);
		stage.centerOnScreen();
	}
	
	@FXML
	protected void logOut() {
		parent = new LoginWindowController();
		btnLogout.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				try {
					parent.LoginWindow(event);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
