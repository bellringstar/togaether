package com.ssafy.dog.domain.dog.service;

import com.ssafy.dog.common.api.Api;
import com.ssafy.dog.domain.dog.dto.request.DogCreateReq;
import com.ssafy.dog.domain.dog.dto.request.DogUpdateReq;
import com.ssafy.dog.domain.dog.dto.response.DogGetRes;
import com.ssafy.dog.domain.dog.entity.Dog;
import org.springframework.validation.Errors;

import java.util.List;

public interface DogService {

    Dog create(DogCreateReq dogCreateReq, Errors errors);

    Dog update(DogUpdateReq dogUpdateReq, Errors errors);

    Api<List<DogGetRes>> getDogs(Long userId);

    Api<DogGetRes> getDogByDogId(Long dogId);

    void deleteDog(Long dogId, Long userId);
}
