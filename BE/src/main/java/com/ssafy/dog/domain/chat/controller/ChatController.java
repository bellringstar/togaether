package com.ssafy.dog.domain.chat.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.dog.common.api.Api;
import com.ssafy.dog.domain.chat.dto.MessageDto;
import com.ssafy.dog.domain.chat.dto.req.ChatRoomReqDto;
import com.ssafy.dog.domain.chat.dto.res.ChatHistoriesResDto;
import com.ssafy.dog.domain.chat.dto.res.ChatListResDto;
import com.ssafy.dog.domain.chat.service.ChatRoomService;
import com.ssafy.dog.domain.chat.service.ChatService;
import com.ssafy.dog.util.SecurityUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ChatController {

	private final ChatService chatService;
	private final ChatRoomService chatRoomService;

	// 채팅방 만들기
	@PostMapping("/chatroom")
	public Api<?> createChatRoom(@RequestBody ChatRoomReqDto chatRoomReqDto) {
		return chatService.createChatRoom(chatRoomReqDto);
	}

	// 유저의 채팅목록 가져오기 (jwt에서 유저정보)
	@GetMapping("/chatroom")
	public Api<List<ChatListResDto>> getChatRoomList() {
		return chatService.getChatList(SecurityUtils.getUserId());
	}

	//채팅 상세보기
	@GetMapping("/chatroom/{roomId}")
	public Api<List<ChatHistoriesResDto>> getChatHistory(@PathVariable Long roomId) {
		return chatService.getChatHistory(roomId, SecurityUtils.getUserId());
	}

	// 채팅방 연결해제
	@DeleteMapping("/chatroom/{roomId}")
	public Api<?> disconnectChat(@PathVariable("roomId") Long chatRoomId) {

		chatRoomService.disconnectChatRoom(chatRoomId, SecurityUtils.getUserId());
		return Api.ok(chatRoomId + " 번 채팅방 : " + SecurityUtils.getUserId() + "나감");
	}

	@DeleteMapping("/chatroom/leave/{roomId}")
	public Api<?> leaveChat(@PathVariable("roomId") Long chatRoomId) {

		chatRoomService.leaveChatRoom(chatRoomId, SecurityUtils.getUserId());
		return Api.ok(chatRoomId + " 번 채팅방 : " + SecurityUtils.getUserId() + "나감");
	}

	@MessageMapping("/message")
	public void sendMessage(@Valid MessageDto message, @Header("Authorization") final String accessToken) {

		chatService.sendMessage(message, accessToken);

	}

}