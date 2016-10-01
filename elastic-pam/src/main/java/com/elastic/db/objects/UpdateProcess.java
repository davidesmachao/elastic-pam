package com.elastic.db.objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.elastic.db.daos.ApplicationDAO;
import com.elastic.db.daos.MainDAO;

/**
 * Class with the information of an update configuration object
 * 
 * @author David Rodriguez
 */
@Entity
@Table(name = "applications")
public class UpdateProcess extends MainObject {

	@Id
	@Column(name = "update_process_id")
	private int updateProcessId;

	@Column(name = "update_process_description", length = 100, nullable = false)
	private String updateProcessDescription;

	public UpdateProcess() {
		// empty constructor
	}

	@Override
	public MainDAO<?> getDAO() {
		return new ApplicationDAO();
	}

	public int getUpdateProcessId() {
		return updateProcessId;
	}

	public void setUpdateProcessId(int updateProcessId) {
		this.updateProcessId = updateProcessId;
	}

	public String getUpdateProcessDescription() {
		return updateProcessDescription;
	}

	public void setUpdateProcessDescription(String updateProcessDescription) {
		this.updateProcessDescription = updateProcessDescription;
	}
}