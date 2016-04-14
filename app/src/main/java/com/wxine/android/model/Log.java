package com.wxine.android.model;

import java.sql.Timestamp;

public class Log implements java.io.Serializable {
	private static final long serialVersionUID = -1098904676415808375L;
	private String id;
	private Timestamp ctime;
	private String level;
	private String client;
	private String ip;
	private String user;
	private String message;

	// Constructors

	/** default constructor */
	public Log() {
	}

	/** minimal constructor */
	public Log(String id, Timestamp ctime, String level, String client,
			String ip, String message) {
		this.id = id;
		this.ctime = ctime;
		this.level = level;
		this.client = client;
		this.ip = ip;
		this.message = message;
	}

	/** full constructor */
	public Log(String id, Timestamp ctime, String level, String client,
			String ip, String user, String message) {
		this.id = id;
		this.ctime = ctime;
		this.level = level;
		this.client = client;
		this.ip = ip;
		this.user = user;
		this.message = message;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getClient() {
		return this.client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}