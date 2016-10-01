package com.elastic.db.daos;

import java.util.List;

import org.hibernate.Session;

import com.elastic.db.connection.ConnectionManager;
import com.elastic.db.objects.UpdateProcess;

/**
 * Applications DAO
 * 
 * @author David Rodriguez
 */
@SuppressWarnings("unchecked")
public class UpdateProcessDAO extends MainDAO<UpdateProcess> {

	public UpdateProcessDAO() {
	}

	@Override
	public void persist(UpdateProcess entity) {
		ConnectionManager con = new ConnectionManager();
		Session session = con.openCurrentSessionwithTransaction();
		session.save(entity);
		con.closeCurrentSessionwithTransaction();
	}

	@Override
	public void update(UpdateProcess entity) {
		ConnectionManager con = new ConnectionManager();
		Session session = con.openCurrentSessionwithTransaction();
		session.update(entity);
		con.closeCurrentSessionwithTransaction();
	}

	@Override
	public UpdateProcess findById(Integer id) {
		return (UpdateProcess) new ConnectionManager().getCurrentSession().get(UpdateProcess.class, id);
	}

	@Override
	public void delete(UpdateProcess entity) {
		ConnectionManager con = new ConnectionManager();
		Session session = con.openCurrentSessionwithTransaction();
		session.delete(entity);
		con.closeCurrentSessionwithTransaction();
	}

	@Override
	public List<UpdateProcess> findAll() {
		return new ConnectionManager().getCurrentSession().createQuery("from UpdateProcess").list();
	}

	@Override
	public void deleteAll() {
		List<UpdateProcess> entityList = findAll();
		for (UpdateProcess entity : entityList) {
			delete(entity);
		}
	}
}