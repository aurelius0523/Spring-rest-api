package com.aurelius.rest.enumeration;

public enum InternalCode {
	INVALID_REQUEST(100);
	
	private int code;

	private InternalCode(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
