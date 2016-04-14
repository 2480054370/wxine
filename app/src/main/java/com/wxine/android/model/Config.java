package com.wxine.android.model;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Config implements java.io.Serializable {
	private static final long serialVersionUID = 1801219483748426473L;
	private String id;
	private User user;
	private Community community;
	private String property;
	private String value;
	
	private List<String> values;
	
	public List<String> getValues() {//分解多选值
		return values;
	}

	public String exist(String value) {//i d e g
		try {
			if(values.contains(value)) {
				return "yes";
			} else {
				return "no";
			}
		} catch	(Exception e) {
			return "no";
		}
	};

	/** default constructor */
	public Config() {
	}

	/** full constructor */
	public Config(String id, User user, Community community, String property, String value) {
		this.id = id;
		this.user = user;
		this.community = community;
		this.property = property;
		this.value = value;
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

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public String getProperty() {
		return this.property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = StringUtils.deleteWhitespace(value);
		
		try {
			this.values = Arrays.asList(value.split(","));
		} catch (Exception e) {
		}
	}

}