package com.ssafy.dog.domain.user.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.dog.domain.user.dto.UserForm;
import com.ssafy.dog.domain.user.entity.User;
import com.ssafy.dog.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public Optional<User> findByUserLoginId(String loginId) {
		return userRepository.findByUserLoginId(loginId);
	}

	@Override
	public Optional<User> findByUserNickname(String nickname) {
		return userRepository.findByUserNickname(nickname);
	}

	@Transactional
	@Override
	public Long signUp(UserForm userForm) throws Exception {
		if (userRepository.findByUserLoginId(userForm.getUserLoginId()).isPresent()) {
			throw new Exception("이미 존재하는 아이디(이메일)입니다.");
		}

		if (userRepository.findByUserNickname(userForm.getUserNickname()).isPresent()) {
			throw new Exception("이미 존재하는 닉네임입니다.");
		}

		User user = userRepository.save(userForm.toEntity());
		user.encodePassword(passwordEncoder);

		// user.addUserAuthority();
		return user.getUserId();
	}
}
