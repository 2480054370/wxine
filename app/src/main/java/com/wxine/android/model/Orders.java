package com.wxine.android.model;

import java.sql.Timestamp;

public class Orders implements java.io.Serializable {
	private static final long serialVersionUID = -3610421870744378087L;
	private String id;
	private User seller;
	private User buyer;
	private User refuser;
	private String code;
	private String subject;
	private String body;
	private String status;
	private Timestamp ctime;
	private Timestamp starttime;
	private Timestamp endtime;
	private String paymode;
	private String invoicetitle;
	private Double total;
	private String currency;
	private Double fare;
	private String stdistrict;
	private String staddress;
	private String stpostcode;
	private String stname;
	private String stphone;
	private String stmobile;
	private String logistics;
	private String waybill;
	private Double weight;
	private Timestamp paytime;
	private Timestamp cftime;
	private String remark;

	public Orders(String id, User buyer, User refuser, String code, String subject,
			String body, String status, Timestamp ctime, Double total, String currency, String remark) {
		this.id = id;
		this.buyer = buyer;
		this.refuser = refuser;
		this.code = code;
		this.subject = subject;
		this.body = body;
		this.status = status;
		this.ctime = ctime;
		this.total = total;
		this.currency = currency;
		this.remark = remark;
	}

	/** default constructor */
	public Orders() {
	}

	/** minimal constructor */
	public Orders(String id, User seller, User buyer, String code, String subject, String body, String status, Timestamp ctime,
			Timestamp starttime, Double total) {
		this.id = id;
		this.seller = seller;
		this.buyer = buyer;
		this.code = code;
		this.subject = subject;
		this.body = body;
		this.status = status;
		this.ctime = ctime;
		this.starttime = starttime;
		this.total = total;
	}

	/** full constructor */
	public Orders(String id, User seller, User buyer, User refuser, String code, String subject, String body,
			String status, Timestamp ctime, Timestamp starttime, Timestamp endtime, String paymode,
			String invoicetitle, Double total, String currency, Double fare, String stdistrict, String staddress,
			String stpostcode, String stname, String stphone, String stmobile, String logistics,
			String waybill, Double weight, Timestamp paytime, Timestamp cftime, String remark) {
		this.id = id;
		this.seller = seller;
		this.buyer = buyer;
		this.refuser = refuser;
		this.code = code;
		this.subject = subject;
		this.body = body;
		this.status = status;
		this.ctime = ctime;
		this.starttime = starttime;
		this.endtime = endtime;
		this.paymode = paymode;
		this.invoicetitle = invoicetitle;
		this.total = total;
		this.currency = currency;
		this.fare = fare;
		this.stdistrict = stdistrict;
		this.staddress = staddress;
		this.stpostcode = stpostcode;
		this.stname = stname;
		this.stphone = stphone;
		this.stmobile = stmobile;
		this.logistics = logistics;
		this.waybill = waybill;
		this.weight = weight;
		this.paytime = paytime;
		this.cftime = cftime;
		this.remark = remark;
	}

	public String getId() {
		return this.id;
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
	
	public User getRefuser() {
		return refuser;
	}

	public void setRefuser(User refuser) {
		this.refuser = refuser;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
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

	public Timestamp getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	public Timestamp getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	public String getPaymode() {
		return this.paymode;
	}

	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}

	public String getInvoicetitle() {
		return this.invoicetitle;
	}

	public void setInvoicetitle(String invoicetitle) {
		this.invoicetitle = invoicetitle;
	}

	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getFare() {
		return this.fare;
	}

	public void setFare(Double fare) {
		this.fare = fare;
	}

	public String getStdistrict() {
		return this.stdistrict;
	}

	public void setStdistrict(String stdistrict) {
		this.stdistrict = stdistrict;
	}

	public String getStaddress() {
		return this.staddress;
	}

	public void setStaddress(String staddress) {
		this.staddress = staddress;
	}

	public String getStpostcode() {
		return this.stpostcode;
	}

	public void setStpostcode(String stpostcode) {
		this.stpostcode = stpostcode;
	}

	public String getStname() {
		return this.stname;
	}

	public void setStname(String stname) {
		this.stname = stname;
	}

	public String getStphone() {
		return this.stphone;
	}

	public void setStphone(String stphone) {
		this.stphone = stphone;
	}

	public String getStmobile() {
		return this.stmobile;
	}

	public void setStmobile(String stmobile) {
		this.stmobile = stmobile;
	}

	public String getLogistics() {
		return this.logistics;
	}

	public void setLogistics(String logistics) {
		this.logistics = logistics;
	}

	public String getWaybill() {
		return this.waybill;
	}

	public void setWaybill(String waybill) {
		this.waybill = waybill;
	}

	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Timestamp getPaytime() {
		return this.paytime;
	}

	public void setPaytime(Timestamp paytime) {
		this.paytime = paytime;
	}

	public Timestamp getCftime() {
		return this.cftime;
	}

	public void setCftime(Timestamp cftime) {
		this.cftime = cftime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}