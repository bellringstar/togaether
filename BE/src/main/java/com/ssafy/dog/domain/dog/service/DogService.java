package com.ssafy.dog.domain.dog.service;

import com.ssafy.dog.domain.dog.dto.DogCreateReq;
import com.ssafy.dog.domain.dog.entity.Dog;

public interface DogService {

	Dog create(DogCreateReq dogCreateReq);
}
