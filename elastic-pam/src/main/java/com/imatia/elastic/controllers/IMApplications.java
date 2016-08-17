package com.imatia.elastic.controllers;

import com.imatia.elastic.db.daos.ApplicationDAO;
import com.imatia.elastic.db.objects.Application;
import com.imatia.elastic.util.FormUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class IMApplications {

	@FXML
	private TableView<Application> applicationsTableView;

	public IMApplications() {
		// Empty constructor
	}

	@FXML
	public void initialize() {
		loadData();

		applicationsTableView.setOnMousePressed(e -> {
			if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {

				Application application = applicationsTableView.getSelectionModel().getSelectedItem();

				Object controller = new FormUtils().showForm(FormUtils.APPLICATION_FORM);

				if (controller instanceof IMApplication) {
					((IMApplication) controller).loadRecord(application);
				}
			}
		});
	}

	@FXML
	public void addApplication() {
		new FormUtils().showForm(FormUtils.APPLICATION_FORM);
	}

	@FXML
	public void removeApplication() {

		Application application = applicationsTableView.getSelectionModel().getSelectedItem();

		ApplicationDAO dao = new ApplicationDAO();

		dao.delete(application);
	}

	/**
	 * Loads form data
	 */
	private void loadData() {
		applicationsTableView.setItems(getDBItems());
	}

	/**
	 * @return a list of all configured applications in the system
	 */
	private ObservableList<Application> getDBItems() {

		ApplicationDAO dao = new ApplicationDAO();

		return FXCollections.observableList(dao.findAll());
	}
}