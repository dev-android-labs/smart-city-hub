package com.persistent.android.sujeet.smartcityhub.domain.model

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
data class Weather(
    val cityName: String,
    val temperature: Double,
    val feelsLike: Double,
    val minTemp: Double,
    val maxTemp: Double,
    val pressure: Int,
    val humidity: Int,
    val condition: String,
    val description: String,
    val iconUrl: String? = null,
    val windSpeed: Double,
    val sunrise: Long,
    val sunset: Long,
    val lat: Double,
    val lon: Double,
    val dataTimestamp: Long,
    val icon: String,
) {
    fun getIconImageUrl(): String {
        return "https://openweathermap.org/img/wn/$icon@2x.png"
    }
}
