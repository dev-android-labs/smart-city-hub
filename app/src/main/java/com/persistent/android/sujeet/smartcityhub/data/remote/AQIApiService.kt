package com.persistent.android.sujeet.smartcityhub.data.remote

import com.persistent.android.sujeet.smartcityhub.data.remote.model.AQIResponse
import com.persistent.android.sujeet.smartcityhub.data.remote.model.WeatherResponse
import com.persistent.android.sujeet.smartcityhub.utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
interface AQIApiService {

    @GET("air_pollution")
    suspend fun getAirQuality(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String = API_KEY,
    ): Response<AQIResponse>
}