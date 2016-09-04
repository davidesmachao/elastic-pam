package com.elastic.controllers.crud.changes;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import com.elastic.ElasticPamApplication;
import com.elastic.controllers.crud.CrudController;

import javafx.beans.Observable;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

public class CrudChangesController {

	/**
	 * A reference to the crud controller
	 */
	CrudController crudController;

	/**
	 * List with the fields that have been modified
	 */
	List<String> modifiedFields = new ArrayList<>();

	/**
	 * 
	 */
	boolean changeControllerEnabled = true;

	public CrudChangesController(CrudController crudController) {
		this.crudController = crudController;

		addActionListeners();
	}

	/**
	 * This method will add an action listener to all fields of the CRUD
	 * controller. These action listeners
	 */
	private void addActionListeners() {
		Field[] controllerFields = crudController.getClass().getDeclaredFields();

		for (Field field : controllerFields) {
			try {

				field.setAccessible(true);

				Object fieldInstance = field.get(crudController);

				if (fieldInstance instanceof TextInputControl) {
					((TextInputControl) fieldInstance).textProperty().addListener(e -> handleChangeListener(e));
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				ElasticPamApplication.getLogger().log(Level.WARNING,
						"Can not set value change listener to field " + field.getName(), e);
			}
		}
	}

	private void handleChangeListener(Observable e) {

		if (!changeControllerEnabled) {
			return;
		}

		if (e instanceof StringProperty) {
			TextField field = (TextField) ((StringProperty) e).getBean();
			if (field != null) {
				modifiedFields.add(field.getId());
			}
		}
	}

	public List<String> getModifiedFields() {
		return modifiedFields;
	}

	public void setChangeControllerEnabled(boolean changeControllerEnabled) {
		this.changeControllerEnabled = changeControllerEnabled;
	}
}