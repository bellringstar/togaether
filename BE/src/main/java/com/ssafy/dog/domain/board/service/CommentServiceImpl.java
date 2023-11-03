package com.ssafy.dog.domain.board.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.dog.common.api.Api;
import com.ssafy.dog.common.error.BoardErrorCode;
import com.ssafy.dog.common.error.CommentErrorCode;
import com.ssafy.dog.common.error.UserErrorCode;
import com.ssafy.dog.common.exception.ApiException;
import com.ssafy.dog.domain.board.dto.CommentDto;
import com.ssafy.dog.domain.board.entity.Board;
import com.ssafy.dog.domain.board.entity.Comment;
import com.ssafy.dog.domain.board.enums.FileStatus;
import com.ssafy.dog.domain.board.repository.BoardRepository;
import com.ssafy.dog.domain.board.repository.CommentRepository;
import com.ssafy.dog.domain.user.entity.User;
import com.ssafy.dog.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final BoardRepository boardRepository;
	private final UserRepository userRepository;
	private final CommentRepository commentRepository;

	@Transactional
	public Api<String> createComment(CommentDto commentDto) {
		Optional<Board> board = boardRepository.findById(commentDto.getBoardId());
		Board curBoard = board.orElseThrow(() -> new ApiException(BoardErrorCode.BOARD_LIST_IS_EMPTY));
		Optional<User> user = userRepository.findByUserId(curBoard.getUser().getUserId());
		User curUser = user.orElseThrow(() -> new ApiException(UserErrorCode.USER_NOT_FOUND));

		if (commentDto.getCommentContent().length() > 50) {
			throw new ApiException(CommentErrorCode.COMMENT_TOO_LONG);
		}
		if (commentDto.getCommentContent().isEmpty()) {
			throw new ApiException(CommentErrorCode.COMMENT_NOT_FOUND);
		}

		Comment comment = Comment.builder()
			.user(curUser)
			.board(curBoard)
			.commentContent(commentDto.getCommentContent())
			.commentStatus(FileStatus.USE)
			.build();
		commentRepository.save(comment);

		return Api.ok(curBoard.getBoardId() + " 번 게시물에 " + curUser.getUserLoginId() + " 의 댓글 등록 성공");
	}
}
