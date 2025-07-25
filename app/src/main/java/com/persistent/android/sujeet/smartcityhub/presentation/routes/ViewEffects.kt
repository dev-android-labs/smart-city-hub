package com.persistent.android.sujeet.smartcityhub.presentation.routes

import com.persistent.android.sujeet.smartcityhub.domain.model.AirQuality
import com.persistent.android.sujeet.smartcityhub.domain.model.Alert
import com.persistent.android.sujeet.smartcityhub.domain.model.Weather

/**
 * Created by SUJEET KUMAR on 7/26/2025
 */
sealed class ViewEffects {
    
    object NavigateBack : ViewEffects()
    data class NavigateToWeatherScreen(val weather: Weather) : ViewEffects()
    data class NavigateToAQIScreen(val aqi: AirQuality) : ViewEffects()
    data class NavigateToAlertScreen(val alert: Alert) : ViewEffects()
    data class ShowToast(val message: String) : ViewEffects()
    data class ShowSnackBar(val message: String) : ViewEffects()
}