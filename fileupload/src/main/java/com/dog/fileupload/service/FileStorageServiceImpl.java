package com.dog.fileupload.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class FileStorageServiceImpl implements FileStorageService {

	private final Path root = Paths.get("uploads");

	@Override
	public void init() {
		try {
			Files.createDirectories(root);
		} catch (IOException e) {
			throw new RuntimeException("업로드 폴더를 생성할 수 없습니다.");
		}
	}

	@Override
	public Mono<String> save(Mono<FilePart> filePartMono) {

		return filePartMono.doOnNext(fp -> log.info("Receiving File: {}",fp.filename())).flatMap(filePart -> {
			String filename = filePart.filename();
			return filePart.transferTo(root.resolve(filename)).then(Mono.just(filename));
		});
	}

	@Override
	public Flux<DataBuffer> load(String filename) {
		try {
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return DataBufferUtils.read(resource, new DefaultDataBufferFactory(), 4096);
			} else {
				throw new RuntimeException("파일을 읽을 수 없습니다.");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.root, 1)
				.filter(path -> !path.equals(this.root))
				.map(this.root::relativize);
		} catch (IOException e) {
			throw new RuntimeException("피을을 읽을 수 없습니다.");
		}
	}
}
