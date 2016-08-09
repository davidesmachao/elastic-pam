package com.imatia.elastic.db.objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "applications")
public class Application {

	@Id
	@Column(name = "application_id")
	private int applicationId;

	@Column(name = "application_description", length = 100, nullable = false)
	private String applicationDescription;

	@Column(name = "database_url", length = 100, nullable = false)
	private String databaseUrl;

	@Column(name = "database_user", length = 50, nullable = false)
	private String databaseUser;

	@Column(name = "database_password", length = 50, nullable = false)
	private String databasePassword;

	@Column(name = "database_backup_folder", length = 500, nullable = false)
	private String databaseBackupFolder;

	@Column(name = "tomcat_folder", length = 500, nullable = false)
	private String tomcatFolder;

	@Column(name = "tomcat_application_name", length = 50, nullable = false)
	private String tomcatApplicationName;

	@Column(name = "tomcat_application_backup_folder", length = 500, nullable = false)
	private String tomcatApplicationBackupFolder;

	@Column(name = "google_account_name", length = 50, nullable = false)
	private String googleAccountName;

	@Column(name = "google_account_password", length = 50, nullable = false)
	private String googleAccountPassword;

	@Column(name = "download_folder", length = 500, nullable = false)
	private String downloadFolder;

	public Application() {
		// empty constructor
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicationDescription() {
		return applicationDescription;
	}

	public void setApplicationDescription(String applicationDescription) {
		this.applicationDescription = applicationDescription;
	}

	public String getDatabaseUrl() {
		return databaseUrl;
	}

	public void setDatabaseUrl(String databaseUrl) {
		this.databaseUrl = databaseUrl;
	}

	public String getDatabaseUser() {
		return databaseUser;
	}

	public void setDatabaseUser(String databaseUser) {
		this.databaseUser = databaseUser;
	}

	public String getDatabasePassword() {
		return databasePassword;
	}

	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}

	public String getDatabaseBackupFolder() {
		return databaseBackupFolder;
	}

	public void setDatabaseBackupFolder(String databaseBackupFolder) {
		this.databaseBackupFolder = databaseBackupFolder;
	}

	public String getTomcatFolder() {
		return tomcatFolder;
	}

	public void setTomcatFolder(String tomcatFolder) {
		this.tomcatFolder = tomcatFolder;
	}

	public String getTomcatApplicationName() {
		return tomcatApplicationName;
	}

	public void setTomcatApplicationName(String tomcatApplicationName) {
		this.tomcatApplicationName = tomcatApplicationName;
	}

	public String getTomcatApplicationBackupFolder() {
		return tomcatApplicationBackupFolder;
	}

	public void setTomcatApplicationBackupFolder(String tomcatApplicationBackupFolder) {
		this.tomcatApplicationBackupFolder = tomcatApplicationBackupFolder;
	}

	public String getGoogleAccountName() {
		return googleAccountName;
	}

	public void setGoogleAccountName(String googleAccountName) {
		this.googleAccountName = googleAccountName;
	}

	public String getGoogleAccountPassword() {
		return googleAccountPassword;
	}

	public void setGoogleAccountPassword(String googleAccountPassword) {
		this.googleAccountPassword = googleAccountPassword;
	}

	public String getDownloadFolder() {
		return downloadFolder;
	}

	public void setDownloadFolder(String downloadFolder) {
		this.downloadFolder = downloadFolder;
	}
}