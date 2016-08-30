package com.elastic.db.daos;

import java.util.List;

import org.hibernate.Session;

import com.elastic.db.connection.ConnectionManager;
import com.elastic.db.objects.Application;

/**
 * Applications DAO
 * 
 * @author David Rodriguez
 */
@SuppressWarnings("unchecked")
public class ApplicationDAO extends MainDAO<Application> {

	public ApplicationDAO() {
	}

	@Override
	public void persist(Application entity) {
		ConnectionManager con = new ConnectionManager();
		Session session = con.openCurrentSessionwithTransaction();
		session.save(entity);
		con.closeCurrentSessionwithTransaction();
	}

	@Override
	public void update(Application entity) {
		ConnectionManager con = new ConnectionManager();
		Session session = con.openCurrentSessionwithTransaction();
		session.update(entity);
		con.closeCurrentSessionwithTransaction();
	}

	@Override
	public Application findById(Integer id) {
		return (Application) new ConnectionManager().getCurrentSession().get(Application.class, id);
	}

	@Override
	public void delete(Application entity) {
		new ConnectionManager().getCurrentSession().delete(entity);
	}

	@Override
	public List<Application> findAll() {
		return new ConnectionManager().getCurrentSession().createQuery("from Application").list();
	}

	@Override
	public void deleteAll() {
		List<Application> entityList = findAll();
		for (Application entity : entityList) {
			delete(entity);
		}
	}
}