package com.app.stateless.jwt.service;

import com.app.stateless.jwt.model.UserEntity;

public interface UserService {
	
	public UserEntity saveUser(UserEntity userEntity);
}
