package com.persistent.android.sujeet.smartcityhub.data.repository

import com.persistent.android.sujeet.smartcityhub.data.Result
import com.persistent.android.sujeet.smartcityhub.data.local.AppDataStorage
import com.persistent.android.sujeet.smartcityhub.data.remote.WeatherApiService
import com.persistent.android.sujeet.smartcityhub.domain.model.City
import com.persistent.android.sujeet.smartcityhub.domain.model.Forecast
import com.persistent.android.sujeet.smartcityhub.domain.model.Weather
import com.persistent.android.sujeet.smartcityhub.domain.repository.WeatherRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
class WeatherRepositoryImp @Inject constructor(val apiService: WeatherApiService) :
    WeatherRepository {
    override fun getCurrentWeather(): Flow<Weather> {
        return AppDataStorage.weatherResponse.map {
            it.toDomain()
        }
    }

    override suspend fun getCurrentWeather(city: City): Flow<Result<Weather>> = flow {

        try {
            val response = apiService.getCurrentWeather(city.cityName)

            if (response.isSuccessful) {
                AppDataStorage.weatherResponse.value = response.body()!!
            } else {
                throw Exception(response.message())
            }

            val weather = AppDataStorage.weatherResponse.value.toDomain()
            emit(Result.SUCCESS(weather))

        } catch (e: Exception) {
            emit(Result.ERROR(e.message.toString()))
        }

    }

    override suspend fun getFiveDayForecast(city: City): Flow<Result<List<Forecast>>> = flow {
        try {
            val response = apiService.getFiveDayForecast(city.cityName)
            if (response.isSuccessful) {

                val forecast = response.body()
                val forecastList = forecast?.list?.map { it.toDomain() }
                emit(Result.SUCCESS(forecastList ?: emptyList()))
            } else {
                throw Exception(response.message())
            }
        } catch (e: Exception) {
            emit(Result.ERROR(e.message.toString()))
        }
    }
}