package com.elastic.controllers;

import com.elastic.util.FormUtils;

import javafx.event.ActionEvent;

public class LeftMenuController {

	/**
	 * Opens application's form on right pane
	 */
	public void showApplicationsForm(ActionEvent event) {
		new FormUtils().showForm(FormUtils.APPLICATIONS_FORM);
	}
}