package com.ssafy.dog.domain.board.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ssafy.dog.domain.board.entity.Board;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	BoardRepository boardRepository;

	@AfterEach
	public void cleanup() {
		boardRepository.deleteAll();
	}

	@Test
	public void baseTimeEntity() {
		LocalDateTime now = LocalDateTime.of(2023, 10, 14, 0, 0, 0);
		boardRepository.save(Board.builder()
			.title("title")
			.content("content")
			.viewCount(0L)
			.build());

		List<Board> boardList = boardRepository.findAll();

		Board board = boardList.get(0);

		System.out.println(
			">>>>>>>>> createDate=" + board.getCreatedDate() + ", modifiedDate=" + board.getModifiedDate());

		Assertions.assertThat(board.getCreatedDate().isAfter(now));
		Assertions.assertThat(board.getModifiedDate().isAfter(now));
	}
}
