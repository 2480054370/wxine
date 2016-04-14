package com.wxine.android.model;

import java.sql.Timestamp;

public class Image implements java.io.Serializable {
	private static final long serialVersionUID = -2118599869960571398L;
	private String id;
	private User user;
	private Info info;
	private Community community;
	private Event event;
	private Record record;
	private String url;
	private String caption;
	private String type;
	private Integer csort;
	private String status;
	private Timestamp ctime;

	/** default constructor */
	public Image() {
	}

	/** minimal constructor */
	public Image(String id, String url, String type, Integer csort, String status, Timestamp ctime) {
		this.id = id;
		this.url = url;
		this.type = type;
		this.csort = csort;
		this.status = status;
		this.ctime = ctime;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Info getInfo() {
		return this.info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCaption() {
		return this.caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getCsort() {
		return this.csort;
	}

	public void setCsort(Integer csort) {
		this.csort = csort;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

}