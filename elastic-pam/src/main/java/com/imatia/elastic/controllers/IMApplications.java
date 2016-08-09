package com.imatia.elastic.controllers;

import com.imatia.elastic.db.daos.ApplicationDAO;
import com.imatia.elastic.db.objects.Application;

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
	}

	/**
	 * Loads form data
	 */
	private void loadData() {
		applicationsTableView.setItems(getDBItems());
		applicationsTableView.refresh();
	}

	/**
	 * @return a list of all configured applications in the system
	 */
	private ObservableList<Application> getDBItems() {

		ApplicationDAO dao = new ApplicationDAO();

		return FXCollections.observableList(dao.findAll());
	}
}