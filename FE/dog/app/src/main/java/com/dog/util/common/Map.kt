package com.dog.util.common

import android.content.pm.PackageManager
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.dog.MainActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(){
    val fusedLocationProviderClient = getFusedLocationProviderClient()
    val mumbai = LatLng(19.0760, 72.8777)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(mumbai, 11f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxWidth(),
        cameraPositionState = cameraPositionState
    )
}
@Composable
fun getFusedLocationProviderClient(): FusedLocationProviderClient {
    val context = LocalContext.current
    return LocationServices.getFusedLocationProviderClient(context)
}
