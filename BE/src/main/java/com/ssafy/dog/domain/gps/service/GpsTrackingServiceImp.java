package com.ssafy.dog.domain.gps.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.dog.common.error.ErrorCode;
import com.ssafy.dog.common.exception.ApiException;
import com.ssafy.dog.domain.gps.dto.GpsTrackingResponse;
import com.ssafy.dog.domain.gps.dto.GpsTrackingSaveRequest;
import com.ssafy.dog.domain.gps.entity.GpsTracking;
import com.ssafy.dog.domain.gps.repository.GpsTrackingRepository;

import io.jsonwebtoken.lang.Collections;
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
		GpsTracking savedEntity = gpsTrackingRepository.save(GpsTracking.toEntity(request));
		return GpsTrackingResponse.toResponse(savedEntity);
	}

	@Override
	public List<GpsTrackingResponse> findTrackingDataByUserLoginId(String userLoginId, String order) {
		List<GpsTracking> trackingList = gpsTrackingRepository.findAllByUserLoginId(userLoginId);

		if (Collections.isEmpty(trackingList)) {
			return java.util.Collections.emptyList();
		}
		// tmp 유저 email : test@mail.com;
		if (order.equalsIgnoreCase("asc")) {
			return trackingList.stream()
				.sorted(Comparator.comparing(GpsTracking::getTrackingDate))
				.map(GpsTrackingResponse::toResponse)
				.collect(Collectors.toList());
		}

		if (order.equalsIgnoreCase("desc")) {
			return trackingList.stream()
				.sorted(Comparator.comparing(GpsTracking::getTrackingDate).reversed())
				.map(GpsTrackingResponse::toResponse)
				.collect(Collectors.toList());
		}

		throw new ApiException(ErrorCode.BAD_REQUEST, "올바른 정렬 기준을 요청해주세요. asc, desc");
	}

}
