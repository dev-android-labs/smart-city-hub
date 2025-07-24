package com.persistent.android.sujeet.smartcityhub.domain.usecases

import com.persistent.android.sujeet.smartcityhub.data.Result
import com.persistent.android.sujeet.smartcityhub.domain.model.AirQuality
import com.persistent.android.sujeet.smartcityhub.domain.model.City
import com.persistent.android.sujeet.smartcityhub.domain.repository.AirQualityRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
class GetAirQualityUseCase @Inject constructor(
    private val airQualityRepository: AirQualityRepository,
) {
    operator fun invoke(): Flow<AirQuality> {
        return airQualityRepository.getAirQuality()
    }

    suspend operator fun invoke(city: City): Flow<Result<AirQuality>> {
        return airQualityRepository.getAirQuality(city)
    }
}