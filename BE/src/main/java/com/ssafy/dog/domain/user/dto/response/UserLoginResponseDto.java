package com.ssafy.dog.domain.user.dto.response;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginResponseDto {

	@Email(message = "로그인 아이디는 이메일 형식이어야 합니다.",
		regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
	private String userLoginId;

	@Size(min = 2, max = 15)
	private String userNickname;

	private String userPicture;

	private String jwt;

	public UserLoginResponseDto(String userLoginId, String userNickname, String userPicture, String jwt) {
		this.userLoginId = userLoginId;
		this.userNickname = userNickname;
		this.userPicture = userPicture;
		this.jwt = jwt;
	}
}
