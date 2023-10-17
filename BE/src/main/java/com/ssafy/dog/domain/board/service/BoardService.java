package com.ssafy.dog.domain.board.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.dog.domain.board.dto.request.BoardCreateRequest;
import com.ssafy.dog.domain.board.dto.response.BoardCreateResponse;
import com.ssafy.dog.domain.board.entity.Board;
import com.ssafy.dog.domain.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;

	@Transactional
	public BoardCreateResponse boardCreate(BoardCreateRequest boardCreateRequest) {
		Board board = boardRepository.save(Board.from(boardCreateRequest));
		boardRepository.flush();
		return BoardCreateResponse.from(board);
	}

}
