package com.app.stateless.jwt.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequest {
	
	private String username;
	
	private String password;
}
