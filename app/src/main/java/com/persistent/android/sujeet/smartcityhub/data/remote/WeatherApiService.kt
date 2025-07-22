package com.persistent.android.sujeet.smartcityhub.data.remote

import com.persistent.android.sujeet.smartcityhub.utils.API_KEY
import com.persistent.android.sujeet.smartcityhub.data.remote.model.ForecastResponse
import com.persistent.android.sujeet.smartcityhub.data.remote.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
interface WeatherApiService {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = API_KEY,
        @Query("units") units: String = "metric", // For Celsius
    ): Response<WeatherResponse>

    @GET("forecast")
    suspend fun getFiveDayForecast(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = API_KEY,
        @Query("units") units: String = "metric",
    ): Response<ForecastResponse>
}