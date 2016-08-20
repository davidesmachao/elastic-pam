package com.imatia.elastic;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.imatia.elastic.db.daos.ApplicationDAO;

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

			openDatabaseConnection();

			primaryStage.show();

		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}

	@Override
	public void stop() throws Exception {
		super.stop();

		closeDatabaseConnection();
	}

	/**
	 * Load application's scene
	 * 
	 * @param primaryStage
	 * @throws IOException
	 */
	private void loadScene(Stage primaryStage) throws IOException {

		// loading FXML resources
		URL emptyFormUrl = getClass().getResource("fxml/formempty.fxml");
		Pane centerPane = FXMLLoader.load(emptyFormUrl);

		URL leftMenuUrl = getClass().getResource("fxml/leftmenu.fxml");
		Pane leftPane = FXMLLoader.load(leftMenuUrl);
		leftPane.setPrefWidth(200);

		URL headerUrl = getClass().getResource("fxml/header.fxml");
		Pane topPane = FXMLLoader.load(headerUrl);
		topPane.setPrefWidth(200);

		// constructing our scene using the static root
		root.setCenter(centerPane);
		root.setLeft(leftPane);
		root.setTop(topPane);
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

	/**
	 * Initialize database connection
	 */
	private void openDatabaseConnection() {
		ApplicationDAO someDao = new ApplicationDAO();

		someDao.openCurrentSession();
	}

	/**
	 * Closes database connection
	 */
	private void closeDatabaseConnection() {
		ApplicationDAO someDao = new ApplicationDAO();

		someDao.closeCurrentSession();
	}
}