package com.elastic.controllers;

import com.elastic.ElasticPamApplication;
import com.elastic.controllers.forms.FormLoader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Common class of all controllers of the application
 * 
 * @author David Rodriguez
 */
public class MainController {

	// private String header;

	/**
	 * Application stage
	 */
	private Stage stage;
	protected boolean showBackButton = false;

	@FXML
	public void initialize() {

		cleanHeader();

		setButtons();
	}

	protected void setButtons() {
		if (showBackButton) {
			showBackButton();
		}
	}

	private void showBackButton() {
		Pane menuBar = (Pane) ElasticPamApplication.getRoot().getTop().lookup("#menu_bar");

		// Create back button inside a StackPane
		StackPane stack = new StackPane();

		Button backButton = new Button();
		backButton.getStyleClass().add("table-button-back");
		backButton.setOnAction(e -> back(e));

		stack.getChildren().add(backButton);
		stack.setAlignment(Pos.CENTER_LEFT);
		StackPane.setMargin(backButton, new Insets(0, 560, 0, 0));

		menuBar.getChildren().add(stack);
		HBox.setHgrow(stack, Priority.ALWAYS);
	}

	protected void back(ActionEvent e) {
		FormLoader.showLastForm();
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	private void cleanHeader() {

		Pane menuBar = (Pane) ElasticPamApplication.getRoot().getTop().lookup("#menu_bar");

		menuBar.getChildren().clear();
	}
}