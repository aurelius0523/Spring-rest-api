package com.aurelius.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurelius.rest.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{
	long countByEmail(String email);
	long countByUserName(String userName);
	UserEntity findByIdOrSlug(String id, String slug);
}
	