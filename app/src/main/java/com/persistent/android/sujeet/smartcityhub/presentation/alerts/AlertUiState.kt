package com.persistent.android.sujeet.smartcityhub.presentation.alerts

import com.persistent.android.sujeet.smartcityhub.domain.model.Alert
import com.persistent.android.sujeet.smartcityhub.domain.model.City

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
data class AlertUiState(
    val cityName: City = City.BANGALORE,
    val alerts: List<Alert> = listOf(
        Alert(
            "Road Closure",
            "Due to heavy traffic, the road is temporarily closed."
        ),
        Alert(
            "Severe Weather",
            "There is a severe thunderstorm approaching."
        ),
        Alert(
            "Power Outage",
            "City power grid is experiencing an outage."
        )
    ),
    val isLoading: Boolean = false,
    val error: String? = null,
)