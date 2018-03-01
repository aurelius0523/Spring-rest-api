package com.aurelius.rest.request;

import javax.validation.constraints.NotNull;

public class CreateUserRequest {
	@NotNull
	private String name;
	
	@NotNull
	private String address;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
