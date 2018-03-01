package com.aurelius.rest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;

import com.aurelius.rest.model.UserModel;

public interface UserService {
	public UserModel getUserByIdOrSlug(String idOrSlug);
	public UserModel createUser(UserModel userModel);
	public UserModel updateUser(String userId, UserModel userModel);
	public UserModel patchUser(String userId, UserModel userModel);
	public Page<UserModel> getUsers(int offset, int limit, Direction direction, String field);
}
