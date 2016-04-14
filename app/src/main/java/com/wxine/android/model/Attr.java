package com.wxine.android.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Attr implements Serializable {
	private static final long serialVersionUID = -1082638829549423745L;
	private String name;
	private Set<String> values = new HashSet<String>();
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<String> getValues() {
		return values;
	}
	
	public void setValues(Set<String> values) {
		this.values = values;
	}
}
