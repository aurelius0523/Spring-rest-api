package com.aurelius.rest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurelius.rest.model.UserModel;
import com.aurelius.rest.request.CreateUserRequest;
import com.aurelius.rest.request.UpdateUserRequest;
import com.aurelius.rest.service.UserService;

@RestController
public class UserController {
	//TODO find a mapping framework
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users/{userId}")
	public UserModel getUserByIdOrSlug(@PathVariable("userId") String idOrSlug) {
		return userService.getUserByIdOrSlug(idOrSlug);
	}
	
	@GetMapping("/users")
	public Page<UserModel> getUsers(@RequestParam(defaultValue = "0", name = "offset") int offset,
			@RequestParam(defaultValue = "2", name = "limit") int limit,
			@RequestParam(defaultValue = "", name = "direction") Direction direction,
			@RequestParam(defaultValue = "id", name = "field") String field) {
		
		return userService.getUsers(offset, limit, direction, field);
	}
	
	@PostMapping("/users")
	public ResponseEntity<UserModel> createUser(@Valid @RequestBody CreateUserRequest request) {
		UserModel userModel = new UserModel();
		userModel.setAddress(request.getAddress());
		userModel.setName(request.getName());
		
		return new ResponseEntity<>(userService.createUser(userModel), HttpStatus.CREATED);
	}
	
	@PutMapping("/users/{userId}")
	public UserModel updateUser(@PathVariable("userId") String userId,
			@Valid @RequestBody UpdateUserRequest request) {
		UserModel userModel = new UserModel();
		userModel.setAddress(request.getAddress());
		userModel.setName(request.getName());
		
		return userService.updateUser(userId, userModel);
	}
	
	// TODO find a way to ignore null fields when updating
//	@PatchMapping("/users/{userId}")
//	public UserModel patchUser(@PathVariable("userId") String userId,
//			@Valid @RequestBody UpdateUserRequest request) {
//		UserModel userModel = new UserModel();
//		userModel.setAddress(request.getAddress());
//		userModel.setName(request.getName());
//		
//		return userService.patchUser(userId, userModel);
//	}
}
