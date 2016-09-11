package com.elastic.util;

import java.io.IOException;
import java.net.URL;

import com.elastic.ElasticPamApplication;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Common form utilities
 * 
 * @author David Rodriguez
 *
 */
public class FormUtils {

	/**
	 * Form with a big dropbox to add files to a new installation
	 */
	public static final String ADD_FILES_TO_UPDATE_FORM = "../fxml/addfilestoupdateform.fxml";
	/**
	 * Form with a table listing all configured applications
	 */
	public static final String APPLICATIONS_FORM = "../fxml/applicationsform.fxml";
	/**
	 * Form to edit or insert a new application
	 */
	public static final String APPLICATION_FORM = "../fxml/applicationform.fxml";

	/**
	 * Shows indicated form on a new window
	 * 
	 * @param formName
	 * @return Form's controller if any
	 */
	public Object showFormOnNewWindow(String formName) {

		FXMLLoader fxmlLoader = null;

		try {
			String url = this.getClass().getResource(formName).toExternalForm();

			fxmlLoader = new FXMLLoader(new URL(url));

			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fxmlLoader.getController();
	}

	/**
	 * Shows indicated form
	 * 
	 * @param formName
	 * @return Form's controller if any
	 */
	public Object showForm(String formName) {
		FXMLLoader fxmlLoader = null;

		try {
			String url = this.getClass().getResource(formName).toExternalForm();

			fxmlLoader = new FXMLLoader(new URL(url));

			Pane paneOne = fxmlLoader.load();

			BorderPane border = ElasticPamApplication.getRoot();
			border.setCenter(paneOne);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return fxmlLoader.getController();
	}
}