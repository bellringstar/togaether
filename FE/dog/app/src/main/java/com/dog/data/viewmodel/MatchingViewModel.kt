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
import com.dog.R
import com.dog.data.model.Person
import com.dog.data.model.user.MatchingApiResponse
import com.dog.data.model.user.MatchingUserResponse
import com.dog.data.repository.MatchingRepository
import com.dog.util.common.RetrofitClient
import com.dog.util.common.RetrofitLocalClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MatchingViewModel : ViewModel() {
    //    private val _users = mutableStateListOf(
//        Person(1, "김1",R.drawable.person_icon, "유저 픽처", "사용자 주소 1"),
//        Person(2, "김2",R.drawable.person_icon, "유저 픽처", "사용자 주소 2"),
//        Person(3, "이3",R.drawable.person_icon, "유저 픽처", "사용자 주소 3"),
//        Person(4, "박4",R.drawable.person_icon, "유저 픽처", "사용자 주소 4"),
//        Person(5, "김5",R.drawable.person_icon, "유저 픽처", "사용자 주소 5"),
//        Person(6, "이6",R.drawable.person_icon, "유저 픽처", "사용자 주소 6")
//    )
    private val _users = mutableStateListOf<MatchingUserResponse>()
    val users: List<MatchingUserResponse> get() = _users
    private val _selectedUserId = mutableStateOf<String?>(null)
    val selectedUserId: State<String?> get() = _selectedUserId

    var lazyListState: LazyListState = LazyListState()
        private set

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
                    }
                } else {
                    Log.e("MatchingViewModel", "Error: ${response.errorBody()?.string()}")
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
        val userIndex = _users.indexOfFirst { it.loginId == userId }
        if (userIndex != -1) {
            pagerState?.let { pager ->
                viewModelScope.launch {
                    pager.scrollToPage(userIndex)
                }
            }
        }
    }
}