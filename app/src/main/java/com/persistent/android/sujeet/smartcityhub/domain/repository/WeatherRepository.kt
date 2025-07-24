package com.persistent.android.sujeet.smartcityhub.domain.repository

import com.persistent.android.sujeet.smartcityhub.data.Result
import com.persistent.android.sujeet.smartcityhub.domain.model.City
import com.persistent.android.sujeet.smartcityhub.domain.model.Forecast
import com.persistent.android.sujeet.smartcityhub.domain.model.Weather
import kotlinx.coroutines.flow.Flow

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
interface WeatherRepository {

    fun getCurrentWeather(): Flow<Weather>

    suspend fun getCurrentWeather(city: City): Flow<Result<Weather>>
    suspend fun getFiveDayForecast(city: City): Flow<Result<List<Forecast>>>
}