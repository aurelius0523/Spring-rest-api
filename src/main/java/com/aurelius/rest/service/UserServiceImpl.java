package com.aurelius.rest.service;

import org.modelmapper.ModelMapper;
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
	private ModelMapper mapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserModel getUserByIdOrSlug(String idOrSlug) {
		UserEntity userEntity = userRepository.findByIdOrSlug(idOrSlug, idOrSlug);
		
		if (userEntity == null) {
			throw new ResourceNotFoundException();
		} else {
			return mapper.map(userEntity, UserModel.class);
		}
	}

	@Override
	public UserModel createUser(UserModel userModel) {
		long isEmailUnique = userRepository.countByEmail(userModel.getEmail());
		
		if (isEmailUnique != 0) {
			throw new ConflictException();
		} else {
			long userCountWithThisUsername = userRepository.countByUserName(userModel.getUserName());
			userModel.setSlug(userModel.generateSlug(userCountWithThisUsername));
			UserEntity savedUserEntity = userRepository.save(mapper.map(userModel, UserEntity.class));
			return mapper.map(savedUserEntity, UserModel.class);
		}
	}
	
	@Override
	public UserModel updateUser(String userId, UserModel userModel) {
		UserEntity entityToSave = mapper.map(userModel, UserEntity.class);
		return mapper.map(userRepository.save(entityToSave), UserModel.class);
	}

	@Override
	public Page<UserModel> getUsers(int offset, int limit, Direction direction, String field) {
		Sort sort = new Sort(new Sort.Order(direction, field));
		Pageable pageable = new PageRequest(offset, limit, sort);
		
		return userRepository.findAll(pageable)
				.map(userEntity -> mapper.map(userEntity, UserModel.class));
	}

	@Override
	public UserModel patchUser(String userId, UserModel userModel) {
		// TODO Auto-generated method stub
		return null;
	}
}
