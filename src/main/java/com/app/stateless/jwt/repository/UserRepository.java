package com.app.stateless.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.stateless.jwt.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
