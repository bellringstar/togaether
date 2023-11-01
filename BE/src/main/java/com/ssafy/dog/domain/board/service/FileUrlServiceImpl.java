package com.ssafy.dog.domain.board.service;

import org.springframework.stereotype.Service;

import com.ssafy.dog.domain.board.repository.FileUrlRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileUrlServiceImpl implements FileUrlService {

	private final FileUrlRepository fileUrlRepository;
	
}
