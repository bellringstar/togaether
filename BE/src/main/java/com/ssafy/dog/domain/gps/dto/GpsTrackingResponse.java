package com.ssafy.dog.domain.gps.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.ssafy.dog.domain.gps.entity.GpsTracking;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GpsTrackingResponse {

	private LocalDateTime trackingDate;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	private List<List<Double>> gpsList;

	@Builder
	public GpsTrackingResponse(LocalDateTime trackingDate, LocalDateTime createdDate, LocalDateTime modifiedDate,
		List<List<Double>> gpsList) {
		this.trackingDate = trackingDate;
		this.gpsList = gpsList;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	public static GpsTrackingResponse toResponse(GpsTracking gpsTracking) {
		return GpsTrackingResponse.builder()
			.gpsList(gpsTracking.getGpsList())
			.trackingDate(gpsTracking.getTrackingDate())
			.createdDate(gpsTracking.getCreatedDate())
			.modifiedDate(gpsTracking.getModifiedDate())
			.build();
	}
}
