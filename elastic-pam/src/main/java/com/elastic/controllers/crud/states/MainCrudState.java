package com.elastic.controllers.crud.states;

import com.elastic.controllers.crud.CrudController;
import com.elastic.db.objects.MainObject;

/**
 * This abstract class represents the different states of a CRUD form.<br>
 * 
 * It also stores the common buttons of CRUD forms: save and remove.
 * 
 * @author David Rodriguez
 */
public abstract class MainCrudState {

	protected CrudController crudController;

	public MainCrudState(CrudController crudController) {
		this.crudController = crudController;
	}

	/**
	 * Change state's form
	 * 
	 * @param state
	 */
	public abstract void setState(State state);

	/**
	 * The action to perform when save button is pressed
	 * 
	 * @param crudController
	 * @param genericObject
	 */
	public abstract void onSaveAction(MainObject genericObject);

	/**
	 * The action to perform when delete button is pressed
	 * 
	 * @param crudController
	 * @param genericObject
	 */
	public abstract void onRemoveAction(MainObject genericObject);
}