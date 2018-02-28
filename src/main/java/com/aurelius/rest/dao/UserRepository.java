package com.aurelius.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurelius.rest.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{
	UserEntity findByName(String name);
	UserEntity findByIdOrSlug(String id, String slug);
}
	