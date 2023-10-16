package com.ssafy.example.common.api;

import com.ssafy.example.common.error.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result {

	private Integer code;
	private String message;
	private String description;

	public static Result ok() {
		return Result.builder()
				.code(ErrorCode.OK.getCode())
				.message(ErrorCode.OK.getDescription())
				.description("성공")
				.build();
	}

	public static Result error(ErrorCode errorCode) {
		return Result.builder()
				.code(errorCode.getCode())
				.message(errorCode.getDescription())
				.description("에러")
				.build();
	}

	public static Result error(ErrorCode errorCode, String description) {
		return Result.builder()
				.code(errorCode.getCode())
				.message(errorCode.getDescription())
				.description(description)
				.build();
	}
}


