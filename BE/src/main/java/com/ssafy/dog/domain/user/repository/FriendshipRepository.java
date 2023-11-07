package com.ssafy.dog.domain.user.repository;

import com.ssafy.dog.domain.user.entity.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
}
