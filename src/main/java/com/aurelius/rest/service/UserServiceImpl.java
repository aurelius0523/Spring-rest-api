package com.aurelius.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
			userModel.generateSlug();
			UserEntity savedUserEntity = userRepository.save(userModel.mapFromModelToEntity());
			return UserModel.mapFromEntityToModel(savedUserEntity);
		}
	}
	
	private boolean isNameUnique(String name) {
		return userRepository.findByName(name) == null;
	}

	@Override
	public UserModel updateUser(String userId, UserModel userModel) {
		UserEntity entityToSave = userModel.mapFromModelToEntity();
		return UserModel.mapFromEntityToModel(userRepository.save(entityToSave));
	}

	@Override
	public Page<UserModel> getUsers(int offset, int limit, Direction direction, String field) {
		Sort sort = new Sort(new Sort.Order(direction, field));
		Pageable pageable = new PageRequest(offset, limit, sort);
		
		return userRepository.findAll(pageable)
				.map(userEntity -> UserModel.mapFromEntityToModel(userEntity));
	}

	@Override
	public UserModel patchUser(String userId, UserModel userModel) {
		// TODO Auto-generated method stub
		return null;
	}
}
