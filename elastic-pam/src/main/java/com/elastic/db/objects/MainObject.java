package com.elastic.db.objects;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.persistence.Column;
import javax.persistence.Id;

import com.elastic.ElasticPamApplication;
import com.elastic.db.daos.MainDAO;

/**
 * This class implements common behaviour of all database objects.
 * 
 * @author David Rodriguez
 */
public abstract class MainObject {

	/**
	 * This method will return object's DAO
	 * 
	 * @return
	 */
	public abstract MainDAO getDAO();

	/**
	 * Set object values with indicated data
	 * 
	 * @param values
	 */
	public void setValues(Map<String, Object> values) {

		for (Field f : this.getClass().getDeclaredFields()) {
			Column column = f.getAnnotation(Column.class);
			Id id = f.getAnnotation(Id.class);
			if (id == null && column != null) {
				f.setAccessible(true);
				String columnName = column.name();

				try {
					Object value = values.get(columnName);
					if (value == null) {
						f.set(this, "null");
					} else {
						f.set(this, values.get(columnName));
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					ElasticPamApplication.getLogger().log(Level.SEVERE,
							"Field not found while trying to set object values by reflection. Maybe the object is not well configured",
							e);
				}
			}
		}
	}

	/**
	 * This method will return a Map of all object fields and their values.
	 * 
	 * @return
	 */
	public Map<String, Object> getFieldValues() {
		Map<String, Object> values = new HashMap<>();

		for (Field f : this.getClass().getDeclaredFields()) {
			Column column = f.getAnnotation(Column.class);
			if (column != null) {
				f.setAccessible(true);
				String columnName = column.name();

				try {
					values.put(columnName, f.get(this));
				} catch (IllegalAccessException e) {
					ElasticPamApplication.getLogger().log(Level.SEVERE,
							"Field not found while trying to access object values by reflection. Maybe the object is not well configured",
							e);
				}
			}
		}

		return values;
	}

	/**
	 * Return a list with the columns annotated as id on the object definition
	 * 
	 * @return
	 */
	public List<String> getIdColumns() {
		List<String> idColumnNames = new ArrayList<>();

		for (Field f : this.getClass().getDeclaredFields()) {
			Id id = f.getAnnotation(Id.class);
			if (id != null) {
				idColumnNames.add(f.getName());
			}
		}

		return idColumnNames;
	}
}