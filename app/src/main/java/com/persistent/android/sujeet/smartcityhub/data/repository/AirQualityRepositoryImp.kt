package com.persistent.android.sujeet.smartcityhub.data.repository

import com.persistent.android.sujeet.smartcityhub.data.Result
import com.persistent.android.sujeet.smartcityhub.data.local.AppDataStorage
import com.persistent.android.sujeet.smartcityhub.data.remote.AQIApiService
import com.persistent.android.sujeet.smartcityhub.domain.model.AirQuality
import com.persistent.android.sujeet.smartcityhub.domain.model.City
import com.persistent.android.sujeet.smartcityhub.domain.repository.AirQualityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
class AirQualityRepositoryImp @Inject constructor(val apiService: AQIApiService) :
    AirQualityRepository {
    override suspend fun getAirQuality(city: City): Flow<Result<AirQuality>> = flow {

        try {
            val result = apiService.getAirQuality(city.lat, city.lon)
            if (result.isSuccessful) {
                AppDataStorage.aqiResponse.value = result.body()!!
            } else {
                throw Exception(result.message())
            }

            val airQuality = AppDataStorage.aqiResponse
            emit(Result.SUCCESS(airQuality.value.toDomain()))

        } catch (e: Exception) {
            emit(Result.ERROR(e.message.toString()))
        }
    }

    override fun getAirQuality(): Flow<AirQuality> {
        return AppDataStorage.aqiResponse.map { it ->
            it.toDomain()
        }
    }
}