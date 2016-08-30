package com.elastic.db.daos;

import java.util.List;

/**
 * The main DAO object will implement transactions and sessions control
 * 
 * @author David Rodriguez
 */
public abstract class MainDAO<T> {

	/**
	 * Persist object in database
	 * 
	 * @param entity
	 */
	public abstract void persist(T entity);

	/**
	 * Updates the object in database
	 * 
	 * @param entity
	 */
	public abstract void update(T entity);

	/**
	 * Returns the object with indicated id
	 * 
	 * @param id
	 * @return
	 */
	public abstract T findById(Integer id);

	/**
	 * Deletes the object from the database
	 * 
	 * @param entity
	 */
	public abstract void delete(T entity);

	/**
	 * Returns all objects
	 * 
	 * @return
	 */
	public abstract List<T> findAll();

	/**
	 * Deletes all objects
	 */
	public abstract void deleteAll();
}