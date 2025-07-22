package com.persistent.android.sujeet.smartcityhub.domain.usecases

import com.persistent.android.sujeet.smartcityhub.domain.model.Forecast
import com.persistent.android.sujeet.smartcityhub.domain.repository.WeatherRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
class GetWeatherForecastUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
) {
    suspend operator fun invoke(cityName: String): Flow<List<Forecast>> =
        weatherRepository.getFiveDayForecast(cityName)
}