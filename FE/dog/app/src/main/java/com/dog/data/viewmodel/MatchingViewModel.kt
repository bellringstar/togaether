package com.dog.data.viewmodel

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dog.data.model.matching.MatchingUserResponse
import com.dog.data.repository.MatchingRepository
import com.dog.util.common.FriendRequestManager
import com.dog.util.common.RetrofitLocalClient
import kotlinx.coroutines.launch

class MatchingViewModel : ViewModel() {

    private val friendRequestManager = FriendRequestManager()

    private val _users = mutableStateListOf<MatchingUserResponse>()
    val users: List<MatchingUserResponse> get() = _users
    private val _selectedUserId = mutableStateOf<String?>(null)
    val selectedUserId: State<String?> get() = _selectedUserId
    private val _isDataLoaded = mutableStateOf(false)
    val isDataLoaded: State<Boolean> get() = _isDataLoaded

    private val _toastMessage = mutableStateOf<String?>(null)
    val toastMessage: State<String?> = _toastMessage


    init {
        loadUsersFromApi()
    }

    private fun loadUsersFromApi() {
        viewModelScope.launch {
            try {
                val apiService = RetrofitLocalClient.instance.create(MatchingRepository::class.java)
                val response = apiService.getMatchingApiResponse()

                if (response.isSuccessful) {
                    response.body()?.body?.let { usersFromApi ->
                        _users.clear()
                        _users.addAll(usersFromApi)
                        _isDataLoaded.value = true
                    }
                } else {
                    Log.e("MatchingViewModel", "Error: ${response.errorBody()?.string()}")
                    _isDataLoaded.value = false
                }
            } catch (e: Exception) {
                Log.e("MatchingViewModel", "Exception", e)

            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    var pagerState: PagerState? = null

    @OptIn(ExperimentalFoundationApi::class)
    fun selectUser(userId: String) {
        _selectedUserId.value = userId
        val userIndex = _users.indexOfFirst { it.userLoginId == userId }
        if (userIndex != -1) {
            pagerState?.let { pager ->
                viewModelScope.launch {
                    pager.scrollToPage(userIndex)
                }
            }
        }
    }

    fun senFriendRequest(receiverNickname: String) {
        viewModelScope.launch {
            val response = friendRequestManager.sendFriendRequest(receiverNickname)
            if (response != null) {
                _toastMessage.value = "친구 신청이 성공."
            } else {
                Log.e("MatchingViewModel", "친구신청 실패")
                _toastMessage.value = "친구 신청에 실패했습니다."
            }
        }
    }

    fun clearToastMessage() {
        _toastMessage.value = null
    }

}