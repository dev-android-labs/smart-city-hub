package com.persistent.android.sujeet.smartcityhub.data.remote.model

import com.google.gson.annotations.SerializedName
import com.persistent.android.sujeet.smartcityhub.domain.model.Forecast

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
data class ForecastResponse(
    @SerializedName("list") val list: List<ForecastItem>,
    @SerializedName("city") val city: City,
)

data class ForecastItem(
    @SerializedName("dt") val dt: Long, // Time of data forecasted, Unix, UTC
    @SerializedName("main") val main: Main,
    @SerializedName("weather") val weather: List<WeatherDetail>,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("pop") val pop: Double, // Probability of precipitation
    @SerializedName("sys") val sys: ForecastSys,
    @SerializedName("dt_txt") val dtTxt: String, // Date and time in UTC, e.g., "2024-07-20 18:00:00"
) {
    fun toDomain(): Forecast {
        return Forecast(
            timestamp = dt,
            temperature = main.temp,
            minTemp = main.tempMin,
            maxTemp = main.tempMax,
            condition = weather.firstOrNull()?.main ?: "N/A",
            description = weather.firstOrNull()?.description ?: "N/A",
            iconUrl = weather.firstOrNull()?.icon?.let { "https://openweathermap.org/img/wn/$it@2x.png" }
        )
    }
}

data class ForecastSys(
    @SerializedName("pod") val pod: String, // Part of the day (d - day, n - night)
)