package com.wxine.android.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Record implements Serializable {
	private static final long serialVersionUID = 1270332398939404921L;
	private String id;
	private User user;
	private String type;
	private Timestamp starttime;
	private Timestamp endtime;
	private String org;
	private String summary;
	private String industry;
	private String major;
	private String edulevel;
	private Integer readcount;
	
	public Record() {
	}

	public Record(String id, User user, String type, Timestamp starttime,
			Timestamp endtime, String org, String summary, String industry,
			String major, String edulevel, Integer readcount) {
		this.id = id;
		this.user = user;
		this.type = type;
		this.starttime = starttime;
		this.endtime = endtime;
		this.org = org;
		this.summary = summary;
		this.industry = industry;
		this.major = major;
		this.edulevel = edulevel;
		this.readcount = readcount;
	}
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getStarttime() {
		return starttime;
	}

	public void setStarttime(Timestamp starttime) {
		try {
			this.starttime = starttime;
		} catch(Exception e) {
			
		}
	}

	public Timestamp getEndtime() {
		return endtime;
	}

	public void setEndtime(Timestamp endtime) {
		try {
			this.endtime = endtime;
		} catch(Exception e) {
			
		}
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getEdulevel() {
		return edulevel;
	}

	public void setEdulevel(String edulevel) {
		this.edulevel = edulevel;
	}

	public Integer getReadcount() {
		return readcount;
	}

	public void setReadcount(Integer readcount) {
		this.readcount = readcount;
	}
}
