package org.one.alura_hotel_g5.views;

import java.io.IOException;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class MainWindowController {
	private LoginWindowController loginWindow;

	@FXML
	private ImageView loginImage;
	@FXML
	private Label login;

	@FXML
	protected void login() {
		loginWindow = new LoginWindowController();
		loginImage.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				try {
					loginWindow.LoginWindow(event);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		login.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				try {
					loginWindow.LoginWindow(event);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
