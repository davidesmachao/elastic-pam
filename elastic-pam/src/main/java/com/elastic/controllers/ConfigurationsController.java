package com.elastic.controllers;

import com.elastic.controllers.forms.FormLoader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ConfigurationsController extends MainController {

	@FXML
	public void openConfigurationApplicationForm(ActionEvent event) {
		FormLoader formLoader = new FormLoader();

		formLoader.showForm(FormLoader.APPLICATIONS_FORM);
	}

	@FXML
	public void openUpdateProcessesForm(ActionEvent event) {
	}

}