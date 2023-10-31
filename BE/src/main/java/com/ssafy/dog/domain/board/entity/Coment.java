package com.ssafy.dog.domain.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ssafy.dog.common.auditing.BaseTimeEntity;
import com.ssafy.dog.domain.user.entity.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coment extends BaseTimeEntity {
	@Id
	@GeneratedValue
	@Column(name = "comment_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id")
	private Board board;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private User user;

	private String comentContent;
	private int comentLikes;

	@Builder
	public Coment(User user, Board board, String content) {
		this.comentContent = content;
		this.user = user;
		this.board = board;
		this.comentLikes = 0;
	}

	// == 연관 관계 메서드 == //
	public void setMember(User user) {
		this.user = user;
		user.getComentListForUser().add(this);
	}

	public void setBoard(Board board) {
		this.board = board;
		board.getComentListForBoard().add(this);
	}
}
