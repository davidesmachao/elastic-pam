package com.elastic.controllers.forms;

import java.io.IOException;
import java.util.Stack;

import com.elastic.ElasticPamApplication;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This class handles the showing of the different forms of the application. It
 * also has a stack with the list of opened forms that could be invoked to show
 * last form.
 * 
 * @author David Rodriguez
 *
 */
public class FormLoader {

	/**
	 * Form with a big dropbox to add files to a new installation
	 */
	public static final String ADD_FILES_TO_UPDATE_FORM = "/com/elastic/fxml/addfilestoupdateform.fxml";
	/**
	 * Form with a table listing all configured applications
	 */
	public static final String APPLICATIONS_FORM = "/com/elastic/fxml/applicationsform.fxml";
	/**
	 * Form with a table listing all configured applications
	 */
	public static final String CONFIGURATIONS_FORM = "/com/elastic/fxml/configurationsform.fxml";
	/**
	 * Form to edit or insert a new application
	 */
	public static final String APPLICATION_FORM = "/com/elastic/fxml/applicationform.fxml";

	/**
	 * Stack with all forms showed on the application
	 */
	private static Stack<String> formStack = new Stack<>();

	/**
	 * Shows last form opened on the application
	 * 
	 * @return
	 */
	public static Object showLastForm() {
		Object controller = null;

		formStack.pop();

		if (!formStack.isEmpty()) {
			controller = new FormLoader().showForm(formStack.pop());
		}

		return controller;
	}

	/**
	 * Shows indicated form on a new window
	 * 
	 * @param formName
	 * @return Form's controller if any
	 */
	public Object showFormOnNewWindow(String formName) {

		FXMLLoader fxmlLoader = null;

		try {
			// Load form
			fxmlLoader = new FXMLLoader(getClass().getResource(formName));

			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root1));
			stage.show();

			// Add it to the stack
			formStack.push(formName);

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
			// Load form
			fxmlLoader = new FXMLLoader(getClass().getResource(formName));

			Pane paneOne = fxmlLoader.load();

			BorderPane border = ElasticPamApplication.getRoot();
			border.setCenter(paneOne);

			// Add it to the stack
			formStack.push(formName);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return fxmlLoader.getController();
	}
}