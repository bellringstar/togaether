package com.ssafy.dog.domain.chat.service;

import org.springframework.stereotype.Service;

import com.ssafy.dog.common.api.Api;
import com.ssafy.dog.domain.chat.dto.Message;
import com.ssafy.dog.domain.chat.repository.ChattingRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChatService {

	private final ChattingRepository chattingRepository;

	public Api<?> createChat(Message message) {
		chattingRepository.save(message.convertEntity());

		return Api.ok("채팅 저장 성공");
	}

}
