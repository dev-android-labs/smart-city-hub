package com.persistent.android.sujeet.smartcityhub.presentation.aqi

import com.persistent.android.sujeet.smartcityhub.domain.model.AirQuality
import com.persistent.android.sujeet.smartcityhub.domain.model.City

/**
 * Created by SUJEET KUMAR on 7/26/2025
 */
data class AirQualityUiState(
    val showCitySelectionDialog: Boolean = false,
    val city: City = City.BANGALORE,
    val aqi: AirQuality? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)



