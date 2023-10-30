package com.ssafy.dog.domain.chat.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.dog.common.error.ChatErrorCode;
import com.ssafy.dog.common.exception.ApiException;
import com.ssafy.dog.domain.chat.entity.redis.ChatRoomUsers;
import com.ssafy.dog.domain.chat.repository.ChatRoomUsersRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ChatRoomService {

	private final ChatRoomUsersRepository chatRoomUsersRepository;

	@Transactional
	public void connectChatRoom(Long chatRoomNo, Long userId) {
		ChatRoomUsers chatRoomUsers = ChatRoomUsers.builder()
			.userId(userId)
			.chatroomNo(chatRoomNo)
			.build();

		chatRoomUsersRepository.save(chatRoomUsers);
	}

	public void disconnectChatRoom(Long chatRoomNo, Long userId) {
		ChatRoomUsers chatRoomUsers = chatRoomUsersRepository.findByChatroomNoAndUserId(chatRoomNo, userId)
			.orElseThrow(() -> new ApiException(ChatErrorCode.ROOM_USER_NOT_FOUND));

		chatRoomUsersRepository.delete(chatRoomUsers);
	}
}
