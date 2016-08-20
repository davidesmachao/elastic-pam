package com.imatia.elastic.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class IMApplication extends CrudController {

	@FXML
	TextField application_description;
	@FXML
	TextField database_backup_folder;
	@FXML
	PasswordField database_password;
	@FXML
	TextField database_url;
	@FXML
	TextField database_user;
	@FXML
	TextField google_account_name;
	@FXML
	PasswordField google_account_password;
	@FXML
	TextField tomcat_application_backup_folder;
	@FXML
	TextField tomcat_application_name;
	@FXML
	TextField tomcat_folder;

	public void lookupDatabaseBackupFolder() {
		// TODO: not implemented method
	}

	public void lookupTomcatFolder() {
		// TODO: not implemented method
	}

	public void lookupTomcatApplicationBackupFolder() {
		// TODO: not implemented method
	}
}