package com.ssafy.dog.domain.gps.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Document(collection = "gps_tracking")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GpsTracking {
	
	@Id
	private String id;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	private List<List<Double>> gpsList;

	public GpsTracking(List<List<Double>> gpsList) {
		this.gpsList = gpsList;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
