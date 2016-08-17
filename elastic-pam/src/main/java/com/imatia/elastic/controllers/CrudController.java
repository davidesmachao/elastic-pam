package com.imatia.elastic.controllers;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.logging.Level;

import com.imatia.elastic.ElasticPamApplication;
import com.imatia.elastic.db.objects.MainObject;

import javafx.scene.control.TextInputControl;

/**
 * Common class of all controllers where common CRUD operations should be
 * managed.
 * 
 * @author David Rodriguez
 */
public abstract class CrudController {

	/**
	 * Loads indicated record on current form.<br>
	 * 
	 * Form controller must have fields with same name than the
	 * 
	 * @param genericObject
	 */
	public void loadRecord(MainObject genericObject) {
		Map<String, Object> objectValues = genericObject.getFieldValues();
		Field[] controllerFields = this.getClass().getDeclaredFields();

		for (Map.Entry<String, Object> value : objectValues.entrySet()) {
			for (Field field : controllerFields) {

				if (field.getName().equals(value.getKey())) {
					try {
						if (field.get(this) instanceof TextInputControl) {
							((TextInputControl) field.get(this)).setText(value.getValue().toString());
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						ElasticPamApplication.getLogger().log(Level.SEVERE,
								"Can not set value " + value.getValue() + " to field " + field.getName(), e);
					}
				}
			}
		}
	}
}