package com.dog.fileupload;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dog.fileupload.service.FileStorageService;

@SpringBootApplication
public class FileUploadApplication implements CommandLineRunner {

	@Resource
	FileStorageService fileStorageService;

	public static void main(String[] args) {
		SpringApplication.run(FileUploadApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
		fileStorageService.init();
	}
}
