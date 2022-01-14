package com.app.stateless.jwt.service;

import org.springframework.stereotype.Service;

import com.app.stateless.jwt.model.UserEntity;
import com.app.stateless.jwt.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	@Override
	public UserEntity saveUser(UserEntity userEntity) {
		return userRepository.save(userEntity);
	}
}
