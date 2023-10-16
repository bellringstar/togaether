package com.ssafy.example.common.excepation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafy.example.common.api.Api;
import com.ssafy.example.common.error.ErrorCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({Exception.class})
	public ResponseEntity<Api<Object>> exception(Exception exception) {
		return ResponseEntity
				.status(500)
				.body(Api.error(ErrorCode.SERVER_ERROR, exception.getMessage()));
	}

}
