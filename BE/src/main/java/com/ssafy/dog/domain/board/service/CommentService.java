package com.ssafy.dog.domain.board.service;

import com.ssafy.dog.common.api.Api;
import com.ssafy.dog.domain.board.dto.CommentDto;

public interface CommentService {
	public Api<String> createComment(CommentDto commentDto);
}
