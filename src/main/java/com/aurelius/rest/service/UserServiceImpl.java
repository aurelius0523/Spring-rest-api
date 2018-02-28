package com.aurelius.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aurelius.rest.dao.UserRepository;
import com.aurelius.rest.entity.UserEntity;
import com.aurelius.rest.exception.ConflictException;
import com.aurelius.rest.exception.ResourceNotFoundException;
import com.aurelius.rest.model.UserModel;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserModel getUserByIdOrSlug(String idOrSlug) {
		UserEntity userEntity = userRepository.findByIdOrSlug(idOrSlug, idOrSlug);
		
		if (userEntity == null) {
			throw new ResourceNotFoundException();
		} else {
			return UserModel.mapFromEntityToModel(userEntity);
		}
	}

	@Override
	public UserModel createUser(UserModel userModel) {
		if (!isNameUnique(userModel.getName())) {
			throw new ConflictException();
		} else {
			UserEntity savedUserEntity = userRepository.save(userModel.mapFromModelToEntity());
			return UserModel.mapFromEntityToModel(savedUserEntity);
		}
	}
	
	private boolean isNameUnique(String name) {
		return userRepository.findByName(name) == null;
	}
}
