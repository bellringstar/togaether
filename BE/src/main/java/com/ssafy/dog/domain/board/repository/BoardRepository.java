package com.ssafy.dog.domain.board.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.ssafy.dog.domain.board.entity.Board;
import com.ssafy.dog.domain.user.entity.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

	private final EntityManager em;

	public void save(Board board) {
		em.persist(board);
	}

	public Board findOne(Long title) {
		return em.find(Board.class, title);
	}

	public List<Board> findAllByUser(User user) {
		return em.createQuery("select m from Board m where m.user = :user", Board.class)
			.setParameter("user", user)
			.getResultList();
	}
}
