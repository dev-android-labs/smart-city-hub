package com.persistent.android.sujeet.smartcityhub.presentation.home

import com.persistent.android.sujeet.smartcityhub.domain.model.AirQuality
import com.persistent.android.sujeet.smartcityhub.domain.model.Alert
import com.persistent.android.sujeet.smartcityhub.domain.model.City
import com.persistent.android.sujeet.smartcityhub.domain.model.Service
import com.persistent.android.sujeet.smartcityhub.domain.model.Weather

/**
 * Created by SUJEET KUMAR on 7/22/2025
 */

data class StatsUiState(
    val city: City = City.BANGALORE,
    val weather: Weather? = Weather(
        cityName = city.cityName,
        temperature = 284.2,
        feelsLike = 282.93,
        minTemp = 283.06,
        maxTemp = 286.82,
        pressure = 1021,
        humidity = 60,
        condition = "Rain",
        description = "moderate rain",
        iconUrl = "https://openweathermap.org/img/wn/02d@2x.png",
        icon = "02d",
        windSpeed = 4.09,
        sunrise = 1726636384,
        sunset = 1726680975,
        dataTimestamp = 1726660758,
        lat = 12.97,
        lon = 77.58
    ),
    val aqi: AirQuality? = AirQuality(3),
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
    val showCitySelectionDialog: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
)

sealed class StatsEvent {


    data class Refresh(val city: City) : StatsEvent()

    object LoadWeather : StatsEvent()
    object LoadAQI : StatsEvent()
    object LoadTraffic : StatsEvent()

    object SettingClicked : StatsEvent()
    object HelpClicked : StatsEvent()
    object CityDialogDismiss : StatsEvent()

    data class CitySelected(val city: City) : StatsEvent()

    object WeatherClicked : StatsEvent()
    object AqiClicked : StatsEvent()
    object TrafficClicked : StatsEvent()

    data class ServiceClicked(val service: Service) : StatsEvent()
    data class AlertClicked(val alert: Alert) : StatsEvent()

}

sealed class ViewEffects() {

    object NavigateBack : ViewEffects()
    data class NavigateToWeatherScreen(val weather: Weather) : ViewEffects()
    data class NavigateToAQIScreen(val aqi: AirQuality) : ViewEffects()
    data class NavigateToAlertScreen(val alert: Alert) : ViewEffects()
    data class ShowToast(val message: String) : ViewEffects()
    data class ShowSnackBar(val message: String) : ViewEffects()
}