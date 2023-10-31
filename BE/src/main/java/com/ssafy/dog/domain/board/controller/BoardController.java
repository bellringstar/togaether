package com.ssafy.dog.domain.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ssafy.dog.common.api.Api;
import com.ssafy.dog.domain.board.dto.BoardDto;
import com.ssafy.dog.domain.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;

	@PostMapping("/board")
	public Api<?> createBoard(@RequestBody BoardDto boarddto) {
		return boardService.createBoard(boarddto);
	}

}
