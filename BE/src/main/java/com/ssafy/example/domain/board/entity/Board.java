package com.ssafy.example.domain.board.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ssafy.example.common.auditing.BaseTimeEntity;
import com.ssafy.example.domain.board.dto.request.BoardCreateRequest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
public class Board extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String content;
	private Long viewCount;

	public static Board from(BoardCreateRequest boardCreateRequest) {
		return Board.builder()
				.id(boardCreateRequest.getId())
				.title(boardCreateRequest.getTitle())
				.content(boardCreateRequest.getContent())
				.viewCount(boardCreateRequest.getViewCount())
				.build();
	}
}
