package com.elastic.controllers.crud;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import com.elastic.ElasticPamApplication;
import com.elastic.controllers.MainController;
import com.elastic.controllers.crud.changes.CrudChangesController;
import com.elastic.controllers.crud.states.CrudInsertState;
import com.elastic.controllers.crud.states.CrudUpdateState;
import com.elastic.controllers.crud.states.MainCrudState;
import com.elastic.controllers.crud.states.State;
import com.elastic.controllers.forms.FormLoader;
import com.elastic.db.objects.MainObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

/**
 * Common class of all controllers where common CRUD operations should be
 * managed.
 * 
 * @author David Rodriguez
 */
public abstract class CrudController extends MainController {

	/**
	 * Controller to monitor all field value changes and its related actions
	 */
	protected CrudChangesController changesController;

	/**
	 * Controller database object class. This is the class where all CRUD
	 * operations will be made
	 */
	private MainObject genericObject;

	/**
	 * Current form state (ie. insert or update)
	 */
	private State state = new State();

	/**
	 * The reference to form's save button
	 */
	private Button saveButton;

	/**
	 * The reference to form's remove button
	 */
	private Button removeButton;

	/**
	 * Constructor
	 * 
	 * @param genericObject
	 *            : the class where all CRUD operations will be made
	 */
	public CrudController(MainObject genericObject) {

		// Stores the class to perform CRUD operations
		this.genericObject = genericObject;
	}

	/**
	 * Add buttons to application header
	 */
	private void setButtons() {

		Pane menuBar = (Pane) ElasticPamApplication.getRoot().getTop().lookup("#menu_bar");

		// Create back button inside a StackPane
		StackPane stack = new StackPane();

		Button backButton = new Button();
		backButton.getStyleClass().add("table-button-back");
		backButton.setOnAction(e -> back(e));

		stack.getChildren().add(backButton);
		stack.setAlignment(Pos.CENTER_LEFT);
		StackPane.setMargin(backButton, new Insets(0, 560, 0, 0));

		menuBar.getChildren().add(stack);
		HBox.setHgrow(stack, Priority.ALWAYS);

		// Create save button
		saveButton = new Button();
		saveButton.getStyleClass().add("table-button-add");
		saveButton.setOnAction(e -> save(e));

		HBox.setHgrow(saveButton, Priority.ALWAYS);
		menuBar.getChildren().add(saveButton);

		// Create remove button
		removeButton = new Button();
		removeButton.getStyleClass().add("table-button-remove");
		removeButton.setOnAction(e -> remove(e));

		menuBar.getChildren().add(removeButton);
	}

	@Override
	public void initialize() {
		super.initialize();

		// Add action listeners to all controller fields
		changesController = new CrudChangesController(this);

		setButtons();

		// Set state
		MainCrudState updateState = new CrudInsertState(this);
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

		changesController.setChangeControllerEnabled(false);

		// Set this object
		this.genericObject = genericObject;

		// Set state
		MainCrudState updateState = new CrudUpdateState(this);
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

		changesController.setChangeControllerEnabled(true);
	}

	/**
	 * This method will be called just before the save action is performed
	 */
	protected void beforeSaveAction() {
		// Empty method. Useful to override
	}

	/**
	 * This method will be called just before the delete action is performed
	 */
	protected void beforeRemoveAction() {
		// Empty method. Useful to override
	}

	/**
	 * This method will be called just after the save action is performed
	 */
	protected void afterSaveAction() {
		// Empty method. Useful to override
	}

	/**
	 * This method will be called just after the delete action is performed
	 */
	protected void afterRemoveAction() {
		// Empty method. Useful to override
	}

	public void back(ActionEvent e) {
		FormLoader.showLastForm();
	}

	/**
	 * Perform save button's action
	 * 
	 * @param event
	 */
	@FXML
	public void save(ActionEvent event) {
		beforeSaveAction();

		state.getState().onSaveAction(genericObject);

		afterSaveAction();
	}

	/**
	 * Perform remove button's action
	 * 
	 * @param event
	 */
	@FXML
	public void remove(ActionEvent event) {
		beforeRemoveAction();

		state.getState().onRemoveAction(genericObject);

		afterRemoveAction();
	}

	/**
	 * This method will return form data
	 * 
	 * @return a Map where the key is the column name and its value is it's
	 *         current value
	 */
	public Map<String, Object> getValues() {
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

		return values;
	}

	/**
	 * @return form's save button
	 */
	public Button getSaveButton() {
		return saveButton;
	}

	/**
	 * @return form's remove button
	 */
	public Button getRemoveButton() {
		return removeButton;
	}

}