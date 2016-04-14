package com.wxine.android.model;

import java.sql.Timestamp;

public class Cmuser implements java.io.Serializable {
	private static final long serialVersionUID = -1987626841534681838L;
	private String id;
	private Community community;
	private User user;
	private String permission;
	private String status;
	private String userstatus;
	private String ps;
	private Timestamp ctime;

	/** default constructor */
	public Cmuser() {
	}

	/** full constructor */
	public Cmuser(String id, Community community, User user, String permission, String status, String userstatus, String ps, Timestamp ctime) {
		this.id = id;
		this.community = community;
		this.user = user;
		this.permission = permission;
		this.status = status;
		this.userstatus = userstatus;
		this.ps = ps;
		this.ctime = ctime;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPermission() {
		return this.permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserstatus() {
		return userstatus;
	}

	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}

	public String getPs() {
		return ps;
	}
	
	public void setPs(String ps) {
		this.ps = ps;
	}

	public Timestamp getCtime() {
		return ctime;
	}
	
	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}
}