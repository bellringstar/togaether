package com.ssafy.dog.domain.gps.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GpsTrackingSaveRequest {

	private LocalDateTime trackingDate;
	private List<List<Double>> gpsList;

	@Builder
	public GpsTrackingSaveRequest(LocalDateTime trackingDate, List<List<Double>> gpsList) {
		this.trackingDate = trackingDate;
		this.gpsList = gpsList;
	}
}
