package com.ssafy.dog.domain.board.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.dog.common.api.Api;
import com.ssafy.dog.domain.board.dto.LikeReqDto;
import com.ssafy.dog.domain.board.service.LikeService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LikeController {

	private final LikeService likeService;

	@PostMapping("/like")
	@Operation(summary = "True : 좋아요 적용 / False : 좋아요 취소")
	public Api<String> applyLike(@RequestBody LikeReqDto likeReqDto) {
		return likeService.likeChange(likeReqDto);
	}

}
