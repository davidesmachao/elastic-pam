package com.imatia.elastic;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MenuBar {

	private static final String MAIN_FORM = "fxml/main.fxml";
	private static final String APPLICATIONS_FORM = "forms/fxml/formapplications.fxml";

	@FXML
	private MenuItem openMainForm;
	@FXML
	private MenuItem openApplicationsForm;
	@FXML
	private MenuItem doExit;

	/**
	 * This event will show the initial form of the application
	 * 
	 * @param event
	 */
	@FXML
	void openMainForm(ActionEvent event) {
		showForm(MAIN_FORM);
	}

	/**
	 * This event handler will open the form to add new applications
	 */
	@FXML
	void openApplicationsForm(ActionEvent event) {
		showForm(APPLICATIONS_FORM);
	}

	/**
	 * Close application
	 * 
	 * @param event
	 */
	@FXML
	void doExit(ActionEvent event) {
		System.exit(0);
	}

	/**
	 * Shows indicated formW
	 * 
	 * @param formName
	 */
	private void showForm(String formName) {
		try {
			URL paneOneUrl = getClass().getResource(formName);
			Pane paneOne = FXMLLoader.load(paneOneUrl);

			BorderPane border = ElasticPamApplication.getRoot();
			border.setCenter(paneOne);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}