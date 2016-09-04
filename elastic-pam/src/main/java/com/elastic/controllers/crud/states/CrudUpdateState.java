package com.elastic.controllers.crud.states;

import java.util.Map;

import com.elastic.controllers.crud.CrudController;
import com.elastic.db.objects.MainObject;

public class CrudUpdateState extends MainCrudState {

	public CrudUpdateState(CrudController crudController) {
		super(crudController);
	}

	@Override
	public void setState(State state) {
		state.setState(this);

		crudController.getSaveButton().setVisible(true);
		crudController.getRemoveButton().setVisible(true);
	}

	@Override
	public void onSaveAction(MainObject genericObject) {
		Map<String, Object> values = crudController.getValues();

		genericObject.setValues(values);

		genericObject.getDAO().update(genericObject);
	}

	@Override
	public void onRemoveAction(MainObject genericObject) {
		Map<String, Object> values = crudController.getValues();

		genericObject.setValues(values);

		genericObject.getDAO().delete(genericObject);
	}
}