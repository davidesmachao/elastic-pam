package com.elastic.controllers.config;

import com.elastic.controllers.MainController;
import com.elastic.controllers.forms.FormLoader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ConfigurationsController extends MainController {

	@FXML
	public void openConfigurationApplicationForm(ActionEvent event) {
		FormLoader formLoader = new FormLoader();

		event.consume();

		formLoader.showForm(FormLoader.APPLICATIONS_FORM);
	}

	@FXML
	public void openUpdateProcessesForm(ActionEvent event) {
		FormLoader formLoader = new FormLoader();

		event.consume();

		formLoader.showForm(FormLoader.UPDATE_PROCESSES_FORM);
	}
}