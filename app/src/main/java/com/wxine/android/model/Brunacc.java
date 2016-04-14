package com.wxine.android.model;

import java.sql.Timestamp;

public class Brunacc implements java.io.Serializable {
	private static final long serialVersionUID = -2769098668419884508L;
	private String id;
	private Bank bank;
	private User user;
	private String status;
	private Double balance;
	private Double getout;
	private Double payin;
	private String paymode;
	private String tabloid;
	private String remarks;
	private String mremarks;
	private Timestamp ctime;

	/** default constructor */
	public Brunacc() {
	}

	/** minimal constructor */
	public Brunacc(String id, Bank bank, String status, Double balance,
			String tabloid, Timestamp ctime) {
		this.id = id;
		this.bank = bank;
		this.status = status;
		this.balance = balance;
		this.tabloid = tabloid;
		this.ctime = ctime;
	}

	/** full constructor */
	public Brunacc(String id, Bank bank, User user, String status,
			Double balance, Double getout, Double payin, String paymode,
			String tabloid, String remarks, String mremarks, Timestamp ctime) {
		this.id = id;
		this.bank = bank;
		this.user = user;
		this.status = status;
		this.balance = balance;
		this.getout = getout;
		this.payin = payin;
		this.paymode = paymode;
		this.tabloid = tabloid;
		this.remarks = remarks;
		this.mremarks = mremarks;
		this.ctime = ctime;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Bank getBank() {
		return this.bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Double getGetout() {
		return this.getout;
	}

	public void setGetout(Double getout) {
		this.getout = getout;
	}

	public Double getPayin() {
		return this.payin;
	}

	public void setPayin(Double payin) {
		this.payin = payin;
	}

	public String getPaymode() {
		return this.paymode;
	}

	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}

	public String getTabloid() {
		return this.tabloid;
	}

	public void setTabloid(String tabloid) {
		this.tabloid = tabloid;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getMremarks() {
		return this.mremarks;
	}

	public void setMremarks(String mremarks) {
		this.mremarks = mremarks;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}
}