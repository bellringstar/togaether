package com.ssafy.dog.domain.user.service;

import com.ssafy.dog.common.api.Api;
import com.ssafy.dog.domain.user.dto.request.UserLoginRequestDto;
import com.ssafy.dog.domain.user.dto.request.UserSignupRequestDto;

public interface UserService {

	Api<?> create(UserSignupRequestDto userSignupRequestDto);

	Api<?> login(UserLoginRequestDto userLoginRequestDto);
}
