package com.elastic.controllers;

import java.io.File;

import com.elastic.ElasticPamApplication;
import com.elastic.controllers.crud.CrudController;
import com.elastic.db.objects.Application;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

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

	public IMApplication() {
		super(new Application());
	}

	public void lookupDatabaseBackupFolder() {
		String directory = getFolderPath();

		if (directory != null) {
			database_backup_folder.setText(directory);
		}
	}

	public void lookupTomcatFolder() {
		String directory = getFolderPath();

		if (directory != null) {
			tomcat_folder.setText(directory);
		}
	}

	public void lookupTomcatApplicationBackupFolder() {
		String directory = getFolderPath();

		if (directory != null) {
			tomcat_application_backup_folder.setText(directory);
		}
	}

	/**
	 * Shows the window to select a folder and returns its absolute path
	 * 
	 * @return
	 */
	private String getFolderPath() {
		String path = null;

		DirectoryChooser chooser = new DirectoryChooser();

		File folder = chooser.showDialog(ElasticPamApplication.getStage());

		if (folder != null) {
			path = folder.getAbsolutePath();
		}

		return path;
	}
}