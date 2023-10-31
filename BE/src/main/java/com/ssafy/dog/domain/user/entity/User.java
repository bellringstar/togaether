package com.ssafy.dog.domain.user.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ssafy.dog.common.auditing.BaseTimeEntity;
import com.ssafy.dog.domain.user.model.UserRole;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor // 얘 지우기
@Builder
@Table(name = "user")
public class User extends BaseTimeEntity implements UserDetails { // 주소 속성 아직 안 들어감
	// Entity 로서의 User 속성 및 메소드들
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(unique = true)
	@Email
	@NonNull
	private String userLoginId;

	@NonNull
	private String userPw;

	@Column(unique = true)
	@NonNull
	private String userNickname;

	@Column(name = "user_role")
	@Enumerated(EnumType.STRING)
	private UserRole userRole;

	@NonNull
	@Column(name = "user_phone")
	@Size(max = 11)
	private String userPhone;

	@Lob
	private String userPicture;

	private String userAboutMe;

	private String userGender;

	@NonNull
	private Boolean userTermsAgreed;

	@NonNull
	private Boolean userIsRemoved;

	private Double userLatitude;

	private Double userLongitude;

	@Column(length = 255)
	private String userAddress;

	// UserDetails 로서의 User 속성 및 메소드들
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(userRole.getValue()));
		return authorities;
	}

	@Override
	public String getUsername() {
		return userLoginId;
	}

	@Override
	public String getPassword() {
		return "Calling Wrong methods";
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
