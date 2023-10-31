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

import com.ssafy.dog.domain.board.enums.Scope;
import com.ssafy.dog.domain.user.entity.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Board")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
	@Id
	@GeneratedValue
	private BigInteger boardId;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	private String boardTitle;

	private String boardContent;

	private int boardLikes;

	private LocalDateTime boardCreatedAt;

	private LocalDateTime boardUpdatedAt;

	@Enumerated(value = EnumType.STRING)
	private Scope boardScope;

	@OneToMany(mappedBy = "board")
	private List<Coment> comentListForBoard = new ArrayList<>();

	@Builder
	public Board(User user, String title, String content, Scope scope) {
		this.user = user;
		this.boardTitle = title;
		this.boardContent = content;
		this.boardCreatedAt = LocalDateTime.now();
		this.boardScope = scope;
	}

	// === 연관 관계 메서드 === //
	public void setMemberFromBoard(User user) {
		this.user = user;
		user.getBoardList().add(this);
	}

}
