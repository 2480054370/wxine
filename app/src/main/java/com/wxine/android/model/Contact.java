package com.wxine.android.model;


public class Contact implements java.io.Serializable {
	private static final long serialVersionUID = -4707858756940963438L;
	private String id;
	private User user;
	private String name;
	private String district;
	private String address;
	private String postcode;
	private String mobile;
	private String phone;
	private String fax;
	private String status;

	// Constructors

	/** default constructor */
	public Contact() {
	}

	/** minimal constructor */
	public Contact(String id, User user, String name, String district,
			String address, String postcode, String status) {
		this.id = id;
		this.user = user;
		this.name = name;
		this.district = district;
		this.address = address;
		this.postcode = postcode;
		this.status = status;
	}

	/** full constructor */
	public Contact(String id, User user, String name, String district,
			String address, String postcode, String mobile, String phone,
			String fax, String status) {
		this.id = id;
		this.user = user;
		this.name = name;
		this.district = district;
		this.address = address;
		this.postcode = postcode;
		this.mobile = mobile;
		this.phone = phone;
		this.fax = fax;
		this.status = status;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}