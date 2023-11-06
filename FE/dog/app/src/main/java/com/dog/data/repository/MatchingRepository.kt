package com.dog.data.repository

import com.dog.data.model.user.MatchingApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface MatchingRepository {
    @GET("/matching/cnadin0@businesswire.com")
    fun getMatchingApiResponse(): Call<MatchingApiResponse>
}