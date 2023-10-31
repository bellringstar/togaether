package com.ssafy.dog.domain.board.dto;

import java.time.LocalDateTime;

import com.ssafy.dog.domain.board.enums.Scope;
import com.ssafy.dog.domain.user.entity.User;

import lombok.Getter;

@Getter
public class BoardDto {
	private User user;
	private String boardTitle;
	private String boardContent;
	private LocalDateTime boardCreatedAt;
	private Scope boardScope;
}
