package com.ssafy.dog.domain.board.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Member {
	@Id
	private Long userId;

	@Column(name = "user_login_id")
	private String loginId;

	private String userPw;
	@Column(name = "user_nickname")
	private String nickName;
	@Column(name = "user_picture")
	private String picture;
	@Column(name = "user_created_at")
	private LocalDateTime created;
	@Column(name = "user_updated_at")
	private LocalDateTime updated;
	@Column(name = "user_about_me")
	private String profile;
	@Column(name = "user_gender")
	@Enumerated(EnumType.STRING)
	private gender gender;
	@Column(name = "user_terms_agreed")
	private Integer agreed;

}
