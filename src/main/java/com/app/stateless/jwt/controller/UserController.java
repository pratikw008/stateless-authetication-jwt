package com.app.stateless.jwt.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.stateless.jwt.model.UserEntity;
import com.app.stateless.jwt.model.UserRequest;
import com.app.stateless.jwt.model.UserResponse;
import com.app.stateless.jwt.service.UserService;
import com.app.stateless.jwt.util.JwtUtil;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;

	private JwtUtil jwtUtil;
	
	public UserController(UserService userService, JwtUtil jwtUtil) {
		super();
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping
	public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity userEntity) {
		UserEntity savedUser = userService.saveUser(userEntity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getUserId()).toUri();
		return ResponseEntity.created(location).body(savedUser);
	}
	
	@PostMapping("/login")
	public UserResponse login(@RequestBody UserRequest userRequest) {
		String generatedToken = jwtUtil.generateToken("A1233", userRequest.getUsername());
		System.out.println(jwtUtil.getUsername(generatedToken));
		return new UserResponse(generatedToken, "Token Generated");    
	}
}
