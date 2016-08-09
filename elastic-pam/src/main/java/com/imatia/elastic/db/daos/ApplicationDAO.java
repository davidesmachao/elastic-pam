package com.imatia.elastic.db.daos;

import java.util.List;

import com.imatia.elastic.db.objects.Application;
import com.imatia.elastic.interfaces.IDAOInterface;

/**
 * Applications DAO
 * 
 * @author David Rodriguez
 */
@SuppressWarnings("unchecked")
public class ApplicationDAO extends MainDAO implements IDAOInterface<Application, Integer> {

	public ApplicationDAO() {
	}

	public void persist(Application entity) {
		getCurrentSession().save(entity);
	}

	public void update(Application entity) {
		getCurrentSession().update(entity);
	}

	public Application findById(Integer id) {
		return (Application) getCurrentSession().get(Application.class, id);
	}

	public void delete(Application entity) {
		getCurrentSession().delete(entity);
	}

	public List<Application> findAll() {
		return getCurrentSession().createQuery("from Application").list();
	}

	public void deleteAll() {
		List<Application> entityList = findAll();
		for (Application entity : entityList) {
			delete(entity);
		}
	}
}