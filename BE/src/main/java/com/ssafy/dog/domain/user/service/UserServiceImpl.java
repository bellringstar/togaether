package com.ssafy.dog.domain.user.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.dog.common.error.UserErrorCode;
import com.ssafy.dog.common.exception.ApiException;
import com.ssafy.dog.domain.user.dto.UserDto;
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
	public Long create(UserDto userDto) {
		if (userRepository.findByUserLoginId(userDto.getUserLoginId()).isPresent()) {
			throw new ApiException(UserErrorCode.EMAIL_EXISTS);
		}

		if (userRepository.findByUserNickname(userDto.getUserNickname()).isPresent()) {
			throw new ApiException(UserErrorCode.NICKNAME_EXISTS);
		}

		if (!userDto.getUserTermsAgreed().equals(true)) {
			throw new ApiException(UserErrorCode.TERMS_NOT_AGREED);
		}

		userDto.setUserPw(passwordEncoder.encode(userDto.getUserPw()));
		User user = userRepository.save(userDto.toEntity());
		return user.getUserId();
	}
}
