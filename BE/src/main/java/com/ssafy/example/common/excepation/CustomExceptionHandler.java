package com.ssafy.example.common.excepation;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafy.example.common.api.Api;
import com.ssafy.example.common.error.ErrorCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Api<Object>> sqlException(DataIntegrityViolationException exception) {

		String errorMessage = "DB에 제약조건에 걸립니다. 저장하는 과정에서 에러가 발생했습니다." + exception.getLocalizedMessage();

		return ResponseEntity
				.status(400)
				.body(
						Api.error(ErrorCode.INVALID_INPUT, errorMessage)
				);
	}

}
