package com.ssafy.dog.domain.board.entity;

import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Board")
public class Board {
	@Id
	@Column(name = "board_id")
	private BigInteger id;
	@ManyToOne
	@JoinColumn(name = "user_Id")
	private Member userId;
	@Column(name = "board_title")
	private String title;
	@Column(name = "board_media_type")
	@Enumerated(value = EnumType.STRING)
	private MediaType mediaType;

	@Column(name = "board_content")
	private String content;

	@Column(name = "board_likes")
	private int likes;

	@Column(name = "board_created_at")
	private LocalDateTime created;

	@Column(name = "board_updated_at")
	private LocalDateTime updated;

	@Column(name = "board_scope")
	@Enumerated(value = EnumType.STRING)
	private Scope scope;

}
