package com.ssafy.dog.domain.chat.controller;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.ssafy.dog.domain.chat.dto.ChatMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {

	private final SimpMessageSendingOperations messagingTemplate;
	// private final JwtUtil jwtUtil;

	@CrossOrigin
	@MessageMapping("/chat/message") //websocket "/pub/chat/message"로 들어오는 메시지 처리
	public void message(ChatMessage message, @Header("Authorization") String Authorization) {

		// String authorization = jwtUtil.extractJwt(Authorization);
		// Object memberId = jwtUtil.parseClaims(authorization).get("memberId");
		Object memberId = 1;
		message.setSender((String)memberId);

		messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(),
			message); // /sub/chat/room/{roomId} - 구독
	}
}