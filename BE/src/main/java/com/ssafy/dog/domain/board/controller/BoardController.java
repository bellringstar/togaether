package com.ssafy.dog.domain.board.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.dog.common.api.Api;
import com.ssafy.dog.common.error.ErrorCode;
import com.ssafy.dog.common.error.UserErrorCode;
import com.ssafy.dog.common.exception.ApiException;
import com.ssafy.dog.domain.board.dto.request.BoardCreateRequest;
import com.ssafy.dog.domain.board.dto.response.BoardCreateResponse;
import com.ssafy.dog.domain.board.service.BoardService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardController {
	private final BoardService boardService;

	@PostMapping
	public Api<BoardCreateResponse> boardCreate(@RequestBody BoardCreateRequest boardCreateRequest) {
		return Api.ok(boardService.boardCreate(boardCreateRequest));
	}

	@GetMapping
	public Api<?> test() {
		return Api.ok("test");
	}

	@GetMapping("/test")
	public Api<?> test2() {
		throw new ApiException(UserErrorCode.USER_NOT_FOUND, "에러 발생 테스트");
	}



}
