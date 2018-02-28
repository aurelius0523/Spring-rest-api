package com.aurelius.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aurelius.rest.model.UserModel;
import com.aurelius.rest.service.UserService;

@RestController
public class PersonController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users/{idOrSlug}")
	public UserModel getuserByIdOrSlug(@PathVariable("idOrSlug") String idOrSlug) {
		return userService.getUserByIdOrSlug(idOrSlug);
	}
	
	@PostMapping("/users")
	public UserModel createUser(@RequestBody UserModel userModel) {
		return userService.createUser(userModel);
	}
}
