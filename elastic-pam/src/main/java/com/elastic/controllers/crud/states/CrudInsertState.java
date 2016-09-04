package com.elastic.controllers.crud.states;

import java.util.Map;

import com.elastic.controllers.crud.CrudController;
import com.elastic.db.objects.MainObject;

public class CrudInsertState extends MainCrudState {

	public CrudInsertState(CrudController crudController) {
		super(crudController);
	}

	@Override
	public void setState(State state) {
		state.setState(this);

		crudController.getSaveButton().setVisible(true);
		crudController.getRemoveButton().setVisible(false);
	}

	@Override
	public void onSaveAction(MainObject genericObject) {
		Map<String, Object> values = crudController.getValues();

		genericObject.setValues(values);

		genericObject.getDAO().persist(genericObject);
	}

	@Override
	public void onRemoveAction(MainObject genericObject) {
		// Do nothing because there is nothing to remove yet
	}
}