package com.elastic.controllers.config.applications;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import com.elastic.ElasticPamApplication;
import com.elastic.controllers.crud.CrudController;
import com.elastic.controllers.forms.FormLoader;
import com.elastic.db.objects.Application;
import com.elastic.oauth.OAuthManager;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

public class ApplicationController extends CrudController {

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

	public ApplicationController() {
		super(new Application());

		showBackButton = true;
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

	@Override
	protected void beforeSaveAction() {
		super.beforeSaveAction();

		List<String> modifiedFields = changesController.getModifiedFields();

		if (modifiedFields.contains(google_account_name.getId())) {
			try {
				OAuthManager.requestAuth(google_account_name.getText());
			} catch (IOException e) {
				ElasticPamApplication.getLogger().log(Level.SEVERE, e.getMessage(), e);
			}
		}
	}

	@Override
	protected void afterSaveAction() {
		super.afterSaveAction();

		new FormLoader().showForm(FormLoader.APPLICATIONS_FORM);
	}

	@Override
	protected void afterRemoveAction() {
		super.afterRemoveAction();

		new FormLoader().showForm(FormLoader.APPLICATIONS_FORM);
	}
}