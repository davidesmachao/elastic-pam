package com.imatia.elastic.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * Main interface for application's DAO objects
 * 
 * @author David Rodriguez
 *
 * @param <T>
 * @param <Id>
 */
public interface IDAOInterface<T, Id extends Serializable> {

	/**
	 * Persist object in database
	 * 
	 * @param entity
	 */
	public void persist(T entity);

	/**
	 * Updates the object in database
	 * 
	 * @param entity
	 */
	public void update(T entity);

	/**
	 * Returns the object with indicated id
	 * 
	 * @param id
	 * @return
	 */
	public T findById(Id id);

	/**
	 * Deletes the object from the database
	 * 
	 * @param entity
	 */
	public void delete(T entity);

	/**
	 * Returns all objects
	 * 
	 * @return
	 */
	public List<T> findAll();

	/**
	 * Deletes all objects
	 */
	public void deleteAll();

}