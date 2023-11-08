package com.dog.util.common

import android.util.Log
import com.dog.data.model.user.FriendResponse
import com.dog.data.repository.FriendRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception

class FriendRequestManager {
    private val apiService = RetrofitLocalClient.instance.create(FriendRepository::class.java)

    suspend fun sendFriendRequest(receiverNickname: String): FriendResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<FriendResponse> = apiService.sendFriendRequest(receiverNickname)
                if (response.isSuccessful) {
                    response.body()
                } else {
                    Log.e("FriendRequestManager", "전송 에러 발생: ${response.errorBody()?.string()}")
                    null
                }
            } catch (e: Exception) {
                Log.e("FriendRequestManager", "예외발생", e)
                null
            }
        }
    }
}