package com.ssafy.dog.domain.chat.entity.redis;

import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.Builder;
import lombok.Getter;

@Getter
@RedisHash(value = "chatRoomUsers")
public class ChatRoomUsers {

	@Id
	private Long id;

	@Indexed
	private Long chatroomNo;

	@Indexed
	private Long userId;
	// private String email;

	@Builder
	public ChatRoomUsers(Long chatroomNo, Long userId) {
		this.chatroomNo = chatroomNo;
		this.userId = userId;
	}
}
