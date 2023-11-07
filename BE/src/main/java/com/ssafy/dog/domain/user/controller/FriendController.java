package com.ssafy.dog.domain.user.controller;

import com.ssafy.dog.common.api.Api;
import com.ssafy.dog.domain.user.dto.response.FriendRequestResDto;
import com.ssafy.dog.domain.user.service.FriendService;
import com.ssafy.dog.domain.user.service.UserService;
import com.ssafy.dog.util.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/friend")
@RequiredArgsConstructor
@RestController
@Slf4j
public class FriendController {
    private final FriendService friendService;
    private final UserService userService;

    @PostMapping("/requests/{receiverNickname}")
    @Operation(summary = "친구 신청")
    public Api<FriendRequestResDto> sendFriendRequest(@RequestParam String receiverNickname) {

        return friendService.sendFriendRequest(SecurityUtils.getUserId(), receiverNickname);
    }
}
