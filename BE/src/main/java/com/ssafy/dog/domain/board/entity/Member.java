package com.ssafy.dog.domain.board.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {
	@Id
	@GeneratedValue
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

	// === 연결 === //
	@OneToMany(mappedBy = "member")
	private List<Board> boardList = new ArrayList<>();

	@OneToMany(mappedBy = "memberId")
	private List<Coment> comentListForUser = new ArrayList<>();

}
