package com.imatia.elastic.controllers;

import com.imatia.elastic.util.FormUtils;

import javafx.event.ActionEvent;

public class MainMenuController {

	/**
	 * Opens application's form on right pane
	 */
	public void showApplicationsForm(ActionEvent event) {
		new FormUtils().showForm(FormUtils.APPLICATIONS_FORM);
	}
}