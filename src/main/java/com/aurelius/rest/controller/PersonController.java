package com.aurelius.rest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aurelius.rest.model.UserModel;
import com.aurelius.rest.request.CreateUserRequest;
import com.aurelius.rest.service.UserService;

@RestController
public class PersonController {
	//TODO find a mapping framework
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users/{idOrSlug}")
	public UserModel getuserByIdOrSlug(@PathVariable("idOrSlug") String idOrSlug) {
		return userService.getUserByIdOrSlug(idOrSlug);
	}
	
	@PostMapping("/users")
	public UserModel createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
		UserModel userModel = new UserModel();
		userModel.setAddress(createUserRequest.getAddress());
		userModel.setName(createUserRequest.getName());
		
		return userService.createUser(userModel);
	}
}
