package com.persistent.android.sujeet.smartcityhub.domain.model

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
data class Forecast(
    val timestamp: Long,
    val temperature: Double,
    val minTemp: Double,
    val maxTemp: Double,
    val condition: String,
    val description: String,
    val iconUrl: String?
)
