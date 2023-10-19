package com.dog.fileupload.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GeneratorType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class FileInfo {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String url;

	public FileInfo(String name, String url) {
		this.name = name;
		this.url = url;
	}

}
