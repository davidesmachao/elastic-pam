package com.elastic.controllers;

import com.elastic.controllers.forms.FormLoader;

import javafx.event.ActionEvent;

public class LeftMenuController {

	public void showNewUpdateForm(ActionEvent event) {
		new FormLoader().showForm(FormLoader.ADD_FILES_TO_UPDATE_FORM);
	}

	/**
	 * Opens application's form on right pane
	 */
	public void showApplicationsForm(ActionEvent event) {
		new FormLoader().showForm(FormLoader.APPLICATIONS_FORM);
	}
}