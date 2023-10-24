package com.ssafy.dog.domain.user.service;

import java.util.Optional;

import com.ssafy.dog.domain.user.dto.UserForm;
import com.ssafy.dog.domain.user.entity.User;

public interface UserService {

	Optional<User> findByUserLoginId(String userLoginId);

	Optional<User> findByUserNickname(String userNickname);

	public Long signUp(UserForm userForm) throws Exception;
}
