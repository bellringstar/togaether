package com.ssafy.dog.domain.board.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.dog.domain.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	List<Board> findBoardByUserUserNickname(String userNickname, Sort sort);

}
