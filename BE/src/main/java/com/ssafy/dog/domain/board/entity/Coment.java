package com.ssafy.dog.domain.board.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Coment {
	@Id
	@GeneratedValue
	@Column(name = "comment_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id")
	private Board boardId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private Member memberId;

	private String comentContent;
	private int comentLikes;
	private LocalDateTime comentCreatedAt;
	private LocalDateTime comentUpdatedAt;

	// === 생성 메서드 === //
	public static Coment createComent(Member member, Board board, String content) {
		Coment coment = new Coment();
		coment.setMemberId(member);
		coment.setBoardId(board);
		coment.setComentContent(content);
		coment.setComentCreatedAt(LocalDateTime.now());
		return coment;
	}

	// == 연관 관계 메서드 == //
	public void setMemberFromComent(Member member) {
		this.memberId = member;
		member.getComentListForUser().add(this);
	}

	public void setBoardFromComent(Board board) {
		this.boardId = board;
		board.getComentListForBoard().add(this);
	}
}
