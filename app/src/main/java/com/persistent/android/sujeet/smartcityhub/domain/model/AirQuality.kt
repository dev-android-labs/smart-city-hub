package com.persistent.android.sujeet.smartcityhub.domain.model

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
data class AirQuality(
    val aqi: Int,
    val component: Component = Component(),
) {
    fun getInfo(): String {
        return when (aqi) {
            1 -> "Good"
            2 -> "Fair"
            3 -> "Moderate"
            4 -> "Poor"
            5 -> "Very Poor"
            else -> "Unknown"
        }
    }
}

data class Component(
    val co: Double = 0.0,
    val no: Double = 0.0,
    val no2: Double = 0.0,
    val o3: Double = 0.0,
    val so2: Double = 0.0,
    val pm2_5: Double = 0.0,
    val pm10: Double = 0.0,
    val nh3: Double = 0.0,
)
