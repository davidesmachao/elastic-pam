package com.elastic.controllers;

import com.elastic.util.FormUtils;

import javafx.event.ActionEvent;

public class LeftMenuController {

	public void showNewUpdateForm(ActionEvent event) {
		new FormUtils().showForm(FormUtils.ADD_FILES_TO_UPDATE_FORM);
	}

	/**
	 * Opens application's form on right pane
	 */
	public void showApplicationsForm(ActionEvent event) {
		new FormUtils().showForm(FormUtils.APPLICATIONS_FORM);
	}
}