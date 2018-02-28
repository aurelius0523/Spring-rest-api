package com.aurelius.rest.service;

import com.aurelius.rest.model.UserModel;

public interface UserService {
	public UserModel getUserByIdOrSlug(String idOrSlug);
	public UserModel createUser(UserModel userModel);
}
