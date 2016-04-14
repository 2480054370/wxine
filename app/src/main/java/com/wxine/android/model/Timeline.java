package com.wxine.android.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Timeline implements Serializable {
	private static final long serialVersionUID = -7580692410298277403L;
	private String id;
	private String type;
	private User user;
	private User tuser;
	private Community community;
	private Event event;
	private Goods goods;
	private Photo photo;
	private Photo video;
	private Info info;
	private Timestamp ctime;
	private String status;
	private Integer level;
	private String ip;
	private String clienttype;
	private Double latitude;
	private Double longitude;
	private String address;
	private String f1;
	private String f2;
	private String f3;
	private String f4;
	private String f5;
	private String mood;
	private String url;

	public Timeline() {
	}

	public Timeline(String id, String type, User user, User tuser, Community community, Event event, Goods goods, Photo photo,
			Photo video, Info info, Timestamp ctime, String status, Integer level,
			String ip, String clienttype, Double latitude, Double longitude, String address, String f1,
			String f2, String f3, String f4, String f5, String mood, String url) {
		this.id = id;
		this.type = type;
		this.user = user;
		this.tuser = tuser;
		this.community = community;
		this.event = event;
		this.goods = goods;
		this.photo = photo;
		this.video = video;
		this.info = info;
		this.ctime = ctime;
		this.status = status;
		this.level = level;
		this.ip = ip;
		this.clienttype = clienttype;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
		this.f1 = f1;
		this.f2 = f2;
		this.f3 = f3;
		this.f4 = f4;
		this.f5 = f5;
		this.mood = mood;
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public User getTuser() {
		return tuser;
	}

	public void setTuser(User tuser) {
		this.tuser = tuser;
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

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public Photo getVideo() {
		return video;
	}

	public void setVideo(Photo video) {
		this.video = video;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getClienttype() {
		return clienttype;
	}

	public void setClienttype(String clienttype) {
		this.clienttype = clienttype;
	}
	
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getF1() {
		return f1;
	}

	public void setF1(String f1) {
		this.f1 = f1;
	}

	public String getF2() {
		return f2;
	}

	public void setF2(String f2) {
		this.f2 = f2;
	}

	public String getF3() {
		return f3;
	}

	public void setF3(String f3) {
		this.f3 = f3;
	}

	public String getF4() {
		return f4;
	}

	public void setF4(String f4) {
		this.f4 = f4;
	}

	public String getF5() {
		return f5;
	}

	public void setF5(String f5) {
		this.f5 = f5;
	}

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
