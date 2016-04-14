package com.wxine.android.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Domain implements Serializable {
	private static final long serialVersionUID = -237174136756979261L;
	private String id;
	private User user;
	private Orderitem orderitem;
	private String name;
	private String icp;
	private String status;
	private Timestamp ctime;
	private Timestamp starttime;
	private Timestamp endtime;
	
	public Domain() {
	}

	public Domain(String id, User user, Orderitem orderitem, String name, String icp, String status,
			Timestamp ctime, Timestamp starttime, Timestamp endtime) {
		this.id = id;
		this.user = user;
		this.orderitem = orderitem;
		this.name = name;
		this.icp = icp;
		this.status = status;
		this.ctime = ctime;
		this.starttime = starttime;
		this.endtime = endtime;
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

	public Orderitem getOrderitem() {
		return orderitem;
	}

	public void setOrderitem(Orderitem orderitem) {
		this.orderitem = orderitem;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcp() {
		return icp;
	}

	public void setIcp(String icp) {
		this.icp = icp;
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

	public Timestamp getStarttime() {
		return starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	public Timestamp getEndtime() {
		return endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}
}
