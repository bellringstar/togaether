package com.ssafy.dog.domain.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.dog.common.api.Api;
import com.ssafy.dog.common.error.ErrorCode;
import com.ssafy.dog.common.exception.ApiException;
import com.ssafy.dog.domain.user.dto.UserDto;
import com.ssafy.dog.domain.user.dto.UserSignupDto;
import com.ssafy.dog.domain.user.repository.UserRepository;
import com.ssafy.dog.domain.user.service.UserService;

@RequestMapping("/user")
@RestController
public class UserController {
	private final UserService userService;
	private final UserRepository userRepository;

	@Autowired
	public UserController(UserService userService, UserRepository userRepository) { // <- RequiredArgsConstructor
		this.userService = userService;
		this.userRepository = userRepository;
	}

	@PostMapping("/signup")
	public Api<Object> signUp(@Valid @RequestBody UserSignupDto userSignupDto) {
		try {
			UserDto userDto = new UserDto();
			userDto.setUserLoginId(userSignupDto.getUserLoginId());
			userDto.setUserPw(userSignupDto.getUserPw1());
			userDto.setUserNickname(userSignupDto.getUserNickname());
			userDto.setUserTermsAgreed(userSignupDto.getUserTermsAgreed());

			userService.create(userDto);

			return Api.ok("회원가입에 성공했습니다.");
		} catch (ApiException e) {
			e.printStackTrace();
			return Api.error(e.getErrorCodeIfs(), e.getErrorDescription());
		} catch (Exception e) {
			e.printStackTrace();
			return Api.error(ErrorCode.SERVER_ERROR, "회원가입 중 오류가 발생했습니다.");
		}
	}
}
