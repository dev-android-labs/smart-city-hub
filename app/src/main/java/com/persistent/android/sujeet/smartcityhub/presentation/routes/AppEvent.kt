package com.persistent.android.sujeet.smartcityhub.presentation.routes

import com.persistent.android.sujeet.smartcityhub.domain.model.Alert
import com.persistent.android.sujeet.smartcityhub.domain.model.City
import com.persistent.android.sujeet.smartcityhub.domain.model.Service

/**
 * Created by SUJEET KUMAR on 7/26/2025
 */
sealed class AppEvent {

    object BackClicked : AppEvent()
    object ActionRefreshClicked : AppEvent()
    object ActionSettingClicked : AppEvent()
    object ActionHelpClicked : AppEvent()

    data class Refresh(val city: City) : AppEvent()

    object LoadWeather : AppEvent()
    object LoadWeatherForecast : AppEvent()
    object LoadAQI : AppEvent()
    object LoadTraffic : AppEvent()

    object CityDialogDismiss : AppEvent()

    data class CityChanged(val city: City) : AppEvent()

    object WeatherClicked : AppEvent()
    object AqiClicked : AppEvent()
    object TrafficClicked : AppEvent()

    data class ServiceClicked(val service: Service) : AppEvent()
    data class AlertClicked(val alert: Alert) : AppEvent()

    object ErrorDialogConfirmClicked : AppEvent()

}