package com.ssafy.dog.domain.chat.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.dog.common.api.Api;
import com.ssafy.dog.domain.chat.dto.MessageDto;
import com.ssafy.dog.domain.chat.dto.req.ChatRoomReqDto;
import com.ssafy.dog.domain.chat.service.ChatRoomService;
import com.ssafy.dog.domain.chat.service.ChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ChatController {

	private final ChatService chatService;
	private final ChatRoomService chatRoomService;

	private final SimpMessageSendingOperations messagingTemplate;
	// private final JwtUtil jwtUtil;

	// 채팅방 만들기
	@PostMapping("/chatroom")
	public Api<?> createChatRoom(@RequestBody ChatRoomReqDto chatRoomReqDto) {
		return chatService.createChatRoom(chatRoomReqDto);
	}

	// 유저의 채팅목록 가져오기 (jwt에서 유저정보)
	@GetMapping("/chatroom")
	public Api<?> getChatRoomList() {
		return chatService.getChatList();
	}

	//채팅 상세보기
	@GetMapping("/chatroom/{roomId}")
	public Api<?> getChatHistory(@PathVariable Long roomId) {
		return chatService.getChatHistory(roomId);
	}

	// 채팅방 연결해제
	@DeleteMapping("/chatroom/{roomId}")
	public Api<?> disconnectChat(@PathVariable("chatroomNo") Long chatRoomNo,
		@Header("Authorization") final String accessToken) {
		Long userId = Long.valueOf(1);
		chatRoomService.disconnectChatRoom(chatRoomNo, userId);
		return ResponseEntity.ok(StatusResponseDto.success());
	}

	// @MessageMapping("/message")
	// public void sendMessage(@Valid MessageDto message, @Header("Authorization") final String accessToken) {
	// 	chatService.sendMessage(message, accessToken);
	// }

	@MessageMapping("/message")
	public void sendMessage(@Valid MessageDto message) {
		chatService.sendMessage(message, "임시 토큰");
	}

}