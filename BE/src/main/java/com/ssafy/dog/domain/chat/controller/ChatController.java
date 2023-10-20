package com.ssafy.dog.domain.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.dog.common.api.Api;
import com.ssafy.dog.domain.chat.dto.ChatMessage;
import com.ssafy.dog.domain.chat.dto.Message;
import com.ssafy.dog.domain.chat.service.ChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ChatController {

	private final ChatService chatService;

	private final SimpMessageSendingOperations messagingTemplate;
	// private final JwtUtil jwtUtil;

	@CrossOrigin
	@MessageMapping("/chat/message") //websocket "/pub/chat/message"로 들어오는 메시지 처리
	// public void message(ChatMessage message, @Header("Authorization") String Authorization) {
	public void message(ChatMessage message) {

		log.info("메시지" + message);
		// String authorization = jwtUtil.extractJwt(Authorization);
		// Object memberId = jwtUtil.parseClaims(authorization).get("memberId");
		String memberId = "1";
		message.setSender(memberId);

		// /sub/chat/room/{roomId} - 구독
		messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);

	}

	@PostMapping("/chat/test")
	public Api<?> createUser(@RequestBody Message message) {
		return chatService.createChat(message);
	}

}