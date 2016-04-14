package com.wxine.android.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Orderitem implements java.io.Serializable {
	private static final long serialVersionUID = 1713151190581780488L;
	private String id;
	private User seller;
	private User buyer;
	private Orders orders;
	private Goods goods;
	private String name;
	private String title;
	private String snap;
	private String attr;
	private Double price;
	private Integer number;
	private String currency;
	private Integer point;
	private Double subtotal;
	private String status;
	private Timestamp ctime;

	/** default constructor */
	public Orderitem() {
	}

	public Orderitem(Orders orders, String id, Goods goods, Integer number, String currency) {// 自定初始化构造器
		this.orders = orders;
		this.id = id;
		this.goods = goods;
		this.name = goods.getName();
		this.title = goods.getTitle();
		this.snap = goods.getContent();
		this.price = goods.getPrice();
		this.number = number;
		this.currency = currency;
		BigDecimal b = new BigDecimal(goods.getPrice() * number);
		Double d = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		this.subtotal = d;
		this.status = "normal";
	}

	/** minimal constructor */
	public Orderitem(String id, User seller, User buyer, Goods goods, Double price, Integer number,
			Double subtotal, String status, Timestamp ctime) {
		this.id = id;
		this.seller = seller;
		this.buyer = buyer;
		this.goods = goods;
		this.price = price;
		this.number = number;
		this.subtotal = subtotal;
		this.status = status;
		this.ctime = ctime;
	}

	/** full constructor */
	public Orderitem(String id, User seller, User buyer, Orders orders, Goods goods, String name,
			String title, String snap, String attr, Double price, Integer number, String currency,
			Integer point, Double subtotal, String status, Timestamp ctime) {
		this.id = id;
		this.seller = seller;
		this.buyer = buyer;
		this.orders = orders;
		this.goods = goods;
		this.name = name;
		this.title = title;
		this.snap = snap;
		this.attr = attr;
		this.price = price;
		this.number = number;
		this.currency = currency;
		this.point = point;
		this.subtotal = subtotal;
		this.status = status;
		this.ctime = ctime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSnap() {
		return snap;
	}

	public void setSnap(String snap) {
		this.snap = snap;
	}

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
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
}