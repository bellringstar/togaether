package com.ssafy.dog.domain.board.dto.response;

import com.ssafy.dog.domain.board.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
public class BoardCreateResponse {
	private Long id;
	private String title;
	private String content;
	private Long viewCount;

	public static BoardCreateResponse from(Board board) {
		return BoardCreateResponse.builder()
			.id(board.getId())
			.title(board.getTitle())
			.content(board.getContent())
			.viewCount(board.getViewCount())
			.build();
	}
}
