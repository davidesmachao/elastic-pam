package com.imatia.elastic;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ElasticPamApplication extends Application {

	/**
	 * Creating a static root to pass to the controller
	 */
	private static BorderPane root = new BorderPane();
	/**
	 * Application logger
	 */
	private static final Logger LOGGER = Logger.getLogger("elastic-pam");

	public static void main(String[] args) {

		// Launch application
		launch(args);

	}

	/**
	 * Just a root getter for the controller to use
	 */
	public static BorderPane getRoot() {
		return root;
	}

	@Override
	public void start(Stage primaryStage) {
		try {

			// Load application
			loadApplication(primaryStage);

			setUserAgentStylesheet(STYLESHEET_MODENA);

		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}

	/**
	 * This method will load and show the application
	 * 
	 * @param primaryStage
	 * @throws IOException
	 */
	private void loadApplication(Stage primaryStage) throws IOException {

		// loading FXML resources
		URL menuBarUrl = getClass().getResource("fxml/menubar.fxml");
		MenuBar bar = FXMLLoader.load(menuBarUrl);

		URL mainUrl = getClass().getResource("fxml/main.fxml");
		Pane paneOne = FXMLLoader.load(mainUrl);

		// constructing our scene using the static root
		root.setTop(bar);
		root.setLeft(paneOne);

		Scene scene = new Scene(root, 640, 480);
		scene.getStylesheets().add(getClass().getResource("css/application.css").toExternalForm());

		primaryStage.setScene(scene);
		primaryStage.show();
	}
}