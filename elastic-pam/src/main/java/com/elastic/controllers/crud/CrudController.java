package com.elastic.controllers.crud;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import com.elastic.ElasticPamApplication;
import com.elastic.controllers.MainController;
import com.elastic.db.objects.MainObject;

import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.HBox;

/**
 * Common class of all controllers where common CRUD operations should be
 * managed.
 * 
 * @author David Rodriguez
 */
public abstract class CrudController extends MainController {

	/**
	 * Controller database object class. This is the class where all CRUD
	 * operations will be made
	 */
	private MainObject genericObject;

	private State state = new State();

	private Button saveButton;

	private Button removeButton;

	/**
	 * Constructor
	 * 
	 * @param genericObject
	 *            : the class where all CRUD operations will be made
	 */
	public CrudController(MainObject genericObject) {

		// Stores the class to perform CRUD operations
		setGenericObject(genericObject);
	}

	@Override
	public void initialize() {
		super.initialize();

		// Add action listeners to all controller fields
		addActionListeners();

		setButtons();

		// Set state
		MainCrudState updateState = new CrudInsertState(saveButton, removeButton);
		updateState.setState(state);
	}

	/**
	 * Loads indicated record on current form.<br>
	 * 
	 * Form controller must have fields with same name than object's columns
	 * 
	 * @param genericObject
	 */
	public void loadRecord(MainObject genericObject) {

		// Set this object
		setGenericObject(genericObject);

		// Set state
		MainCrudState updateState = new CrudUpdateState(saveButton, removeButton);
		updateState.setState(state);

		// Load record
		Map<String, Object> objectValues = genericObject.getFieldValues();
		Field[] controllerFields = this.getClass().getDeclaredFields();

		for (Map.Entry<String, Object> value : objectValues.entrySet()) {
			for (Field field : controllerFields) {

				if (field.getName().equals(value.getKey())) {
					try {

						field.setAccessible(true);

						Object fieldInstance = field.get(this);

						if (fieldInstance instanceof TextInputControl) {
							((TextInputControl) field.get(this)).setText(value.getValue().toString());
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						ElasticPamApplication.getLogger().log(Level.WARNING,
								"Can not set value " + value.getValue() + " to field " + field.getName(), e);
					}
				}
			}
		}
	}

	/**
	 * This method will add an action listener to all fields of the CRUD
	 * controller. These action listeners
	 */
	private void addActionListeners() {
		Field[] controllerFields = this.getClass().getDeclaredFields();

		for (Field field : controllerFields) {
			try {

				field.setAccessible(true);

				Object fieldInstance = field.get(this);

				if (fieldInstance instanceof TextField) {
					((TextField) fieldInstance).textProperty().addListener(e -> handleChangeListener(e));
				} else if (fieldInstance instanceof CheckBox) {
					((CheckBox) fieldInstance).setOnInputMethodTextChanged(e -> handleInputMethod(e));
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				ElasticPamApplication.getLogger().log(Level.WARNING,
						"Can not set value change listener to field " + field.getName(), e);
			}
		}
	}

	/**
	 * Add buttons to application header
	 */
	private void setButtons() {

		HBox menuBar = (HBox) ElasticPamApplication.getRoot().getTop().lookup("#menu_bar");

		// Create save button
		saveButton = new Button();
		saveButton.getStyleClass().add("table-button-add");
		saveButton.setOnAction(e -> save(e));

		menuBar.getChildren().add(saveButton);

		// Create remove button
		removeButton = new Button();
		removeButton.getStyleClass().add("table-button-remove");
		removeButton.setOnAction(e -> remove(e));

		menuBar.getChildren().add(removeButton);

	}

	private void handleInputMethod(InputMethodEvent e) {
		System.out.println();
	}

	private void handleChangeListener(Observable e) {
		System.out.println();
	}

	public MainObject getGenericObject() {
		return genericObject;
	}

	public void setGenericObject(MainObject genericObject) {
		this.genericObject = genericObject;
	}

	@FXML
	public void save(ActionEvent event) {
		Map<String, Object> values = new HashMap<>();

		Field[] controllerFields = this.getClass().getDeclaredFields();

		for (Field field : controllerFields) {
			try {

				field.setAccessible(true);

				Object fieldInstance = field.get(this);
				Object value = null;

				if (fieldInstance instanceof TextField) {
					value = ((TextField) fieldInstance).textProperty().getValue();
				} else if (fieldInstance instanceof CheckBox) {
					value = ((CheckBox) fieldInstance).textProperty().getValue();
				}

				values.put(field.getName(), value);

			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		getGenericObject().setValues(values);

		getGenericObject().getDAO().update(getGenericObject());
	}

	@FXML
	public void remove(ActionEvent event) {
		getGenericObject().getFieldValues();
	}
}