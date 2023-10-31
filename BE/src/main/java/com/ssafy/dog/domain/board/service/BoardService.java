package com.ssafy.dog.domain.board.service;

import java.math.BigInteger;

import org.springframework.stereotype.Service;

import com.ssafy.dog.common.api.Api;
import com.ssafy.dog.domain.board.dto.BoardDto;
import com.ssafy.dog.domain.board.entity.Board;
import com.ssafy.dog.domain.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;

	// 게시글 작성, media table에 사용할 boardId 반환
	public BigInteger join(Board board) {
		boardRepository.save(board);
		return board.getBoardId();
	}

	public Api<?> createBoard(BoardDto boardDto) {
		Board board = Board.builder().build();
		boardRepository.save(board);

		return Api.ok(board.getBoardId() + "게시글 등록 성공");
	}

}
