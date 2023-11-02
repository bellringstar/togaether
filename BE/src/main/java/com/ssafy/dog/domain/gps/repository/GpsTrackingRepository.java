package com.ssafy.dog.domain.gps.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ssafy.dog.domain.gps.entity.GpsTracking;

public interface GpsTrackingRepository extends MongoRepository<GpsTracking, String> {

	List<GpsTracking> findAllByUserLoginId(String userLoginId);
}
