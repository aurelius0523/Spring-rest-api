package com.aurelius.rest.model;

public class UserModel {
	private String userId;
	private String firstName;
	private String lastName;
	private String userName;
	private String address;
	private String slug;
	private String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String generateSlug() {
		return generateSlug(0);
	}
	public String generateSlug(long lastId) {
		if (lastId == 0) {
			return userName;
		} else {
			return userName + "." + (lastId + 1);
		}
	}
}
