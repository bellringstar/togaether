package com.ssafy.dog.domain.board.dto;

import java.time.LocalDateTime;

import com.ssafy.dog.domain.board.enums.Scope;

import lombok.Getter;

@Getter
public class BoardDto {
	private Long userId;
	private String boardTitle;
	private String boardContent;
	private LocalDateTime boardCreatedAt;
	private Scope boardScope;
}
