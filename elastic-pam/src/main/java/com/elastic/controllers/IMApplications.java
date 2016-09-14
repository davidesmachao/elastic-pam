package com.elastic.controllers;

import com.elastic.controllers.crud.CrudController;
import com.elastic.controllers.forms.FormLoader;
import com.elastic.db.daos.ApplicationDAO;
import com.elastic.db.objects.Application;
import com.elastic.db.objects.MainObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class IMApplications extends MainController {

	@FXML
	private TableView<Application> applicationsTableView;

	public IMApplications() {
		super();
	}

	@Override
	@FXML
	public void initialize() {
		super.initialize();

		loadData();

		applicationsTableView.setOnMousePressed(e -> {
			if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {

				MainObject application = applicationsTableView.getSelectionModel().getSelectedItem();

				Object controller = new FormLoader().showForm(FormLoader.APPLICATION_FORM);

				if (controller instanceof CrudController) {
					((CrudController) controller).loadRecord(application);
				}
			}
		});
	}

	@FXML
	public void addApplication() {
		new FormLoader().showForm(FormLoader.APPLICATION_FORM);
	}

	@FXML
	public void removeApplication() {

		MainObject application = applicationsTableView.getSelectionModel().getSelectedItem();

		application.getDAO().delete(application);

		loadData();
	}

	/**
	 * Loads applications from database and sets them on their table
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