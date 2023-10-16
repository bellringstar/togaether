package com.ssafy.example.domain.board.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardCreateRequest {
	private Long id;
	private String title;
	private String content;
	private Long viewCount;
}
