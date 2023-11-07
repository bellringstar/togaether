package com.ssafy.dog.domain.user.service;

import com.ssafy.dog.common.api.Api;
import com.ssafy.dog.common.error.UserErrorCode;
import com.ssafy.dog.common.exception.ApiException;
import com.ssafy.dog.domain.user.dto.request.FriendRequestReqDto;
import com.ssafy.dog.domain.user.dto.response.FriendRequestResDto;
import com.ssafy.dog.domain.user.entity.FriendRequest;
import com.ssafy.dog.domain.user.entity.User;
import com.ssafy.dog.domain.user.model.FriendRequestStatus;
import com.ssafy.dog.domain.user.repository.FriendRequestRepository;
import com.ssafy.dog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class FriendServiceImpl implements FriendService {
    private final UserRepository userRepository;
    private final FriendRequestRepository friendRequestRepository;

    @Transactional
    @Override
    public Api<FriendRequestResDto> sendFriendRequest(Long senderId, String receiverNickname) {
        // 1. 입력값으로부터 발신자와 수신자를 찾음
        User sender = userRepository.findByUserId(senderId)
                .orElseThrow(() -> new ApiException(UserErrorCode.SENDER_NOT_FOUND));

        User receiver = userRepository.findByUserNickname(receiverNickname)
                .orElseThrow(() -> new ApiException(UserErrorCode.RECEIVER_NOT_FOUND));

        if (sender.equals(receiver)) { // 2. 발신자와 수신자가 동일한지 검사
            throw new ApiException(UserErrorCode.I_AM_MY_OWN_FRIEND);
        }

        // 3. 기존의 친구 요청이 있는지 검사
        Optional<FriendRequest> myFriendRequest = friendRequestRepository.findBySenderUserIdAndReceiverUserId(sender.getUserId(), receiver.getUserId());
        if (myFriendRequest.isPresent()) {
            // 3.1 상태에 따른 분기 처리
            FriendRequestStatus status = myFriendRequest.get().getStatus();
            switch (status) {
                case PENDING:
                    throw new ApiException(UserErrorCode.REQUEST_ALREADY_SENT);
                case ACCEPTED:
                    throw new ApiException(UserErrorCode.ALREADY_FRIENDS);
                case DECLINED:
                    throw new ApiException(UserErrorCode.PREVIOUSLY_DECLINED);
                default:
                    throw new ApiException(UserErrorCode.WRONG_STATUS_TYPE);
            }
        }

        // 4. 양방향 검사
        Optional<FriendRequest> theirFriendRequest = friendRequestRepository.findBySenderUserIdAndReceiverUserId(receiver.getUserId(), sender.getUserId());
        if (myFriendRequest.isPresent()) {
            // 4.1 상태에 따른 분기 처리
            FriendRequestStatus status = myFriendRequest.get().getStatus();
            switch (status) {
                case PENDING:
                    throw new ApiException(UserErrorCode.THEIR_REQUEST_ALREADY_SENT);
                case ACCEPTED:
                    throw new ApiException(UserErrorCode.THEIR_ALREADY_FRIENDS);
                case DECLINED:
                    throw new ApiException(UserErrorCode.THEIR_PREVIOUSLY_DECLINED);
                default:
                    throw new ApiException(UserErrorCode.THEIR_WRONG_STATUS_TYPE);
            }
        }

        FriendRequestReqDto friendRequestReqDto = new FriendRequestReqDto();

        FriendRequest friendRequest = friendRequestReqDto.toEntity(sender, receiver, FriendRequestStatus.PENDING);

        // 5. Save FriendRequest entity
        friendRequestRepository.save(friendRequest);

        // 6. return
        FriendRequestResDto friendRequestResDto = new FriendRequestResDto(friendRequest.getSender().getUserNickname(), friendRequest.getReceiver().getUserNickname(), friendRequest.getStatus());

        return Api.ok(friendRequestResDto);

    }
}
