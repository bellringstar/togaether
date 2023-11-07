package com.ssafy.dog.domain.user.repository;

import com.ssafy.dog.domain.user.entity.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    Optional<FriendRequest> findBySenderUserIdAndReceiverUserId(Long senderUserId, Long receiverUserId);
}
