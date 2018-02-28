package com.aurelius.rest.model;

import com.aurelius.rest.entity.UserEntity;

public class UserModel {
	private String userId;
	private String name;
	private String address;
	private String slug;
	
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
	public static UserModel mapFromEntityToModel(UserEntity userEntity) {
		UserModel model = new UserModel();
		model.setUserId(userEntity.getId());
		model.setAddress(userEntity.getAddress());
		model.setName(userEntity.getName());
		model.setSlug(userEntity.getSlug());
		return model;
	}
	public UserEntity mapFromModelToEntity() {
		UserEntity userEntity = new UserEntity();
		userEntity.setName(name);
		userEntity.setAddress(address);
		userEntity.setSlug(generateSlug());
		return userEntity;
	}
	public String generateSlug() {
		return name + Math.random();
	}
}
