package com.wxine.android.model;

import java.sql.Timestamp;

public class Follower implements java.io.Serializable {
	private static final long serialVersionUID = -4245777732913956942L;
	private String id;
	private User user;
	private String follow;
	private String type;
	private String status;
	private Timestamp ctime;
	
	public Follower() {
	}

	public Follower(String id, User user, String follow, String type, String status, Timestamp ctime) {
		this.id = id;
		this.user = user;
		this.follow = follow;
		this.type = type;
		this.status = status;
		this.ctime = ctime;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getFollow() {
		return follow;
	}
	
	public void setFollow(String follow) {
		this.follow = follow;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Timestamp getCtime() {
		return ctime;
	}
	
	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}
}
