package com.imatia.elastic;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

	public static Logger getLogger() {
		return LOGGER;
	}

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

			loadScene(primaryStage);

			loadStyleSheets(primaryStage);

			loadConfiguration(primaryStage);

			primaryStage.show();

		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}

	/**
	 * Load application's scene
	 * 
	 * @param primaryStage
	 * @throws IOException
	 */
	private void loadScene(Stage primaryStage) throws IOException {

		// loading FXML resources
		URL emptyUrl = getClass().getResource("fxml/formempty.fxml");
		Pane emptyPane = FXMLLoader.load(emptyUrl);

		URL mainUrl = getClass().getResource("fxml/main.fxml");
		Pane paneOne = FXMLLoader.load(mainUrl);
		paneOne.setPrefWidth(200);

		// constructing our scene using the static root
		root.setCenter(emptyPane);
		root.setLeft(paneOne);
		root.setTop(null);
		root.setBottom(null);
		root.setRight(null);

		primaryStage.setScene(new Scene(root, 900, 650));
	}

	/**
	 * Load application styles
	 * 
	 * @param primaryStage
	 */
	private void loadStyleSheets(Stage primaryStage) {
		primaryStage.getScene().getStylesheets().add(getClass().getResource("css/nav.css").toExternalForm());

		setUserAgentStylesheet(STYLESHEET_MODENA);
	}

	/**
	 * Load main configurations
	 * 
	 * @param primaryStage
	 */
	private void loadConfiguration(Stage primaryStage) {
		primaryStage.setResizable(false);
		primaryStage.setTitle("Elastic PAM");
	}
}