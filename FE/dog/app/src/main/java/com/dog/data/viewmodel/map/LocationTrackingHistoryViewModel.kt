package com.dog.data.viewmodel.map

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dog.data.model.gps.TrackingHistory
import com.dog.data.repository.GpsRepository
import com.dog.util.common.RetrofitLocalClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LocationTrackingHistoryViewModel : ViewModel() {

    private val _trackingHistory = MutableStateFlow<List<TrackingHistory>>(emptyList())
    val trackingHistory = _trackingHistory.asStateFlow()
    fun getTrackingHistory() {
        viewModelScope.launch {
            try {
//                val apiService = RetrofitClient.getInstance().create(GpsRepository::class.java)
                val apiService = RetrofitLocalClient.instance.create(GpsRepository::class.java)
                val retrofitResponse = apiService.getTrackingHistory()
                if (retrofitResponse.isSuccessful) {
                    Log.i("TrackingHistory", "Data get from server successfully")
                    _trackingHistory.value = retrofitResponse.body()!!
                } else {
                    Log.e(
                        "TrackingHistory",
                        "Failed to get data: ${retrofitResponse.errorBody()?.string()}"
                    )
                }
            } catch (e: Exception) {
                Log.e("TrackingHistory", "Error getting data to server", e)
            }
        }
    }
    init {
        getTrackingHistory()
    }
}