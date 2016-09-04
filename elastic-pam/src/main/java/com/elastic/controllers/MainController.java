package com.elastic.controllers;

import com.elastic.ElasticPamApplication;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
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

	@FXML
	public void initialize() {

		cleanHeader();
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	private void cleanHeader() {

		HBox menuBar = (HBox) ElasticPamApplication.getRoot().getTop().lookup("#menu_bar");

		menuBar.getChildren().clear();
	}
}