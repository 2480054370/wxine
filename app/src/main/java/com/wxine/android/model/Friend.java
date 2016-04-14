package com.wxine.android.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Friend implements Serializable {
	private static final long serialVersionUID = 8849750950226814177L;
	private String id;
	private User me;
	private User other;
	private String tag;
	private String permission;
	private String status;
	private String ps;
	private Timestamp ctime;
	
	private Set<String> tags = new HashSet<String>();
	
	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public Friend() {
	}

	public Friend(String id, User me, User other, Timestamp ctime) {
		this.id = id;
		this.me = me;
		this.other = other;
		this.ctime = ctime;
	}

	public Friend(String id, User me, User other, String tag, String permission, String status, String ps, Timestamp ctime) {
		this.id = id;
		this.me = me;
		this.other = other;
		this.tag = tag;
		this.permission = permission;
		this.status = status;
		this.ps = ps;
		this.ctime = ctime;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public User getMe() {
		return me;
	}
	
	public void setMe(User me) {
		this.me = me;
	}
	
	public User getOther() {
		return other;
	}
	
	public void setOther(User other) {
		this.other = other;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
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
