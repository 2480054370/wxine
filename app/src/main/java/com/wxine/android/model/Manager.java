package com.wxine.android.model;

import java.sql.Timestamp;

public class Manager implements java.io.Serializable {
	private static final long serialVersionUID = -7759320758400183888L;
	private String id;
	private String account;
	private String password;
	private String status;
	private String email;
	private String name;
	private String image;
	private String ctype;
	private String nickname;
	private String idcard;
	private Timestamp ctime;

	/** default constructor */
	public Manager() {
	}

	/** minimal constructor */
	public Manager(String id, String account, String password,
			String status, String email, String name, String ctype,
			Timestamp ctime) {
		this.id = id;
		this.account = account;
		this.password = password;
		this.status = status;
		this.email = email;
		this.name = name;
		this.ctype = ctype;
		this.ctime = ctime;
	}

	/** full constructor */
	public Manager(String id, String account, String password,
			String status, String email, String name, String image,
			String ctype, String nickname, String idcard, Timestamp ctime) {
		this.id = id;
		this.account = account;
		this.password = password;
		this.status = status;
		this.email = email;
		this.name = name;
		this.image = image;
		this.ctype = ctype;
		this.nickname = nickname;
		this.idcard = idcard;
		this.ctime = ctime;
	}
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCtype() {
		return this.ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}
}