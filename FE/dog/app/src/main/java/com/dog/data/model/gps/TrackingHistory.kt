package com.dog.data.model.gps

import java.util.Date

data class TrackingHistory(
    val id: String,
    val trackingDate: Date,
    val createdDate: Date,
    val modifiedDate: Date?,
    val gpsPoints: GpsPoints,
    val runningTime: String
)

data class GpsPoints(
    val gpsPoints: List<List<Double>>
)
