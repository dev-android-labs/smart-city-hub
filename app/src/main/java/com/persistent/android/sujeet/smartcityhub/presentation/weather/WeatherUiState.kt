package com.persistent.android.sujeet.smartcityhub.presentation.weather

import com.persistent.android.sujeet.smartcityhub.domain.model.City
import com.persistent.android.sujeet.smartcityhub.domain.model.Forecast
import com.persistent.android.sujeet.smartcityhub.domain.model.Weather

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */

data class WeatherUiState(
    val city: City = City.BANGALORE,
    val weather: Weather? = null,
    val forecasts: List<Forecast>? = null,
    val showCitySelectionDialog: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
)