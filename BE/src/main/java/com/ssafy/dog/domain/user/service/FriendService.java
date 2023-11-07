package com.ssafy.dog.domain.user.service;

import com.ssafy.dog.common.api.Api;
import com.ssafy.dog.domain.user.dto.response.FriendRequestResDto;

public interface FriendService {
    Api<FriendRequestResDto> sendFriendRequest(Long senderId, String receiverNickname);

    Api<FriendRequestResDto> declineFriendRequest(Long declinerId, String requesterNickname);
}
