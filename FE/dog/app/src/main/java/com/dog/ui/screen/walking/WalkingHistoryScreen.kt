package com.dog.ui.screen.walking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dog.data.model.gps.GpsPoints
import com.dog.data.model.gps.TrackingHistory
import com.dog.data.viewmodel.map.LocationTrackingHistoryViewModel
import com.dog.data.viewmodel.map.LocationTrackingViewModel
import com.dog.ui.theme.DogTheme

@Composable
fun TrackingHistoryScreen() {
    val viewModel = LocationTrackingHistoryViewModel()
    DogTheme {
        TrackingHistoryPage(viewModel = viewModel)
    }
}

@Composable
fun TrackingHistoryPage(viewModel: LocationTrackingHistoryViewModel) {
    val trackingHistoryState = viewModel.trackingHistory.collectAsState()

    DogTheme {
        LazyColumn {
            items(trackingHistoryState.value?: emptyList()) {
                trackingHistory -> TrackingHistoryItem(trackingHistory = trackingHistory)
            }
        }
    }
}

@Composable
fun TrackingHistoryItem(trackingHistory: TrackingHistory) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Date: ${trackingHistory.trackingDate}")
            Text(text = "Running Time: ${trackingHistory.runningTime}")
            // gpsPoints를 처리하여 지도에 Polyline으로 표시하는 로직을 추가할 수 있습니다.
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TrackingHistoryScreen()
}


