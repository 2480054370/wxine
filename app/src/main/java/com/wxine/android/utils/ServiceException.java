package com.wxine.android.utils;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = -5947042846949101170L;
	private String code;
	private String message;

	public ServiceException(String message) {
		super(message);
		this.message = message;
	}

	public ServiceException(String message, String code) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
