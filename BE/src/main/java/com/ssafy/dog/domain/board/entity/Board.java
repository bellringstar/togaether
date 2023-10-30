package com.ssafy.dog.domain.board.entity;

import static javax.persistence.FetchType.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Board")
@Getter
@Setter
public class Board {
	@Id
	@GeneratedValue
	private BigInteger boardId;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_Id")
	private Member member;

	private String boardTitle;

	private String boardContent;

	private int boardLikes;

	private LocalDateTime boardCreatedAt;

	private LocalDateTime boardUpdatedAt;

	@Enumerated(value = EnumType.STRING)
	private Scope boardScope;

	// === 연관 관계 메서드 === //
	@OneToMany(mappedBy = "boardId")
	private List<Coment> comentListForBoard = new ArrayList<>();

	public void setMemberFromBoard(Member member) {
		this.member = member;
		member.getBoardList().add(this);
	}

	// === 생성 메서드 === //
	public static Board createBoard(Member member, String title, String content, Scope scope) {
		Board board = new Board();
		board.setBoardTitle(title);
		board.setMember(member);
		board.setBoardContent(content);
		board.setBoardScope(scope);
		board.setBoardCreatedAt(LocalDateTime.now());
		return board;
	}

}
