package com.persistent.android.sujeet.smartcityhub.presentation.weather

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
sealed class WeatherEvent {
    data class LoadWeather(val cityName: String) : WeatherEvent()
    data class RefreshWeather(val cityName: String) : WeatherEvent()
}