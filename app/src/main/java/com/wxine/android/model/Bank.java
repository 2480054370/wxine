package com.wxine.android.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Bank implements java.io.Serializable {
	private static final long serialVersionUID = -2224694910387383260L;
	private String id;
	private User user;
	private String currency;
	private String account;
	private String status;
	private Double balance;
	private String remarks;
	private Timestamp ctime;
	private Set<Brunacc> brunaccs = new HashSet<Brunacc>(0);

	/** default constructor */
	public Bank() {
	}

	/** minimal constructor */
	public Bank(String id, User user, String currency, String account,
			String status, Double balance, Timestamp ctime) {
		this.id = id;
		this.user = user;
		this.currency = currency;
		this.account = account;
		this.status = status;
		this.balance = balance;
		this.ctime = ctime;
	}

	/** full constructor */
	public Bank(String id, User user, String currency, String account,
			String status, Double balance, String remarks, Timestamp ctime,
			Set<Brunacc> brunaccs) {
		this.id = id;
		this.user = user;
		this.currency = currency;
		this.account = account;
		this.status = status;
		this.balance = balance;
		this.remarks = remarks;
		this.ctime = ctime;
		this.brunaccs = brunaccs;
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

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public Set<Brunacc> getBrunaccs() {
		return this.brunaccs;
	}

	public void setBrunaccs(Set<Brunacc> brunaccs) {
		this.brunaccs = brunaccs;
	}
}