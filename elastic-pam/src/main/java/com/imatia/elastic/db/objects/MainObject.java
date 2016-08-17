package com.imatia.elastic.db.objects;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import javax.persistence.Column;

import com.imatia.elastic.ElasticPamApplication;

/**
 * This class implements common behaviour of all database objects.
 * 
 * @author David Rodriguez
 */
public class MainObject {

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
}