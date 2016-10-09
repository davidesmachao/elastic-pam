package com.elastic.controllers.config.updateprocesses;

import com.elastic.controllers.MainController;
import com.elastic.controllers.crud.CrudController;
import com.elastic.controllers.forms.FormLoader;
import com.elastic.db.daos.UpdateProcessDAO;
import com.elastic.db.objects.MainObject;
import com.elastic.db.objects.UpdateProcess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class UpdateProcessesController extends MainController {

	@FXML
	private TableView<UpdateProcess> updateProcessesTableView;

	public UpdateProcessesController() {
		super();

		showBackButton = true;
	}

	@Override
	@FXML
	public void initialize() {
		super.initialize();

		loadData();

		updateProcessesTableView.setOnMousePressed(e -> {
			if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {

				MainObject updateProcess = updateProcessesTableView.getSelectionModel().getSelectedItem();

				Object controller = new FormLoader().showForm(FormLoader.UPDATE_PROCESS_FORM);

				if (controller instanceof CrudController) {
					((CrudController) controller).loadRecord(updateProcess);
				}
			}
		});
	}

	@FXML
	public void addUpdateProcess() {
		new FormLoader().showForm(FormLoader.UPDATE_PROCESS_FORM);
	}

	@FXML
	public void removeUpdateProcess() {

		MainObject updateProcess = updateProcessesTableView.getSelectionModel().getSelectedItem();

		updateProcess.getDAO().delete(updateProcess);

		loadData();
	}

	/**
	 * Loads applications from database and sets them on their table
	 */
	private void loadData() {
		updateProcessesTableView.setItems(getDBItems());
	}

	/**
	 * @return a list of all configured applications in the system
	 */
	private ObservableList<UpdateProcess> getDBItems() {

		UpdateProcessDAO dao = new UpdateProcessDAO();

		return FXCollections.observableList(dao.findAll());
	}
}