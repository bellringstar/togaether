package com.ssafy.dog.domain.gps.service;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.dog.common.error.ErrorCode;
import com.ssafy.dog.common.exception.ApiException;
import com.ssafy.dog.domain.gps.dto.GpsTrackingResponse;
import com.ssafy.dog.domain.gps.dto.GpsTrackingSaveRequest;
import com.ssafy.dog.domain.gps.entity.GpsTracking;
import com.ssafy.dog.domain.gps.repository.GpsTrackingRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class GpsTrackingServiceImp implements GpsTrackingService {
	private final GpsTrackingRepository gpsTrackingRepository;

	@Override
	@Transactional
	public GpsTrackingResponse saveGpsTrackingData(GpsTrackingSaveRequest request) {
		if (Objects.isNull(request)) {
			throw new ApiException(ErrorCode.BAD_REQUEST, "트래킹 정보를 입력하세요");
		}
		GpsTracking savedEntity = gpsTrackingRepository.save(GpsTracking.toEntity(request));
		return GpsTrackingResponse.toResponse(savedEntity);
	}

	public void findTrackingDataByUserLoginId() {

	}
}
