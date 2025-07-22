package com.persistent.android.sujeet.smartcityhub.data.remote.model

import com.google.gson.annotations.SerializedName
import com.persistent.android.sujeet.smartcityhub.domain.model.Weather

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
data class WeatherResponse(
    @SerializedName("coord") val coord: Coord,
    @SerializedName("weather") val weather: List<WeatherDetail>,
    @SerializedName("main") val main: Main,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("sys") val sys: Sys,
    @SerializedName("name") val name: String,
    @SerializedName("dt") val dt: Long, // Time of data calculation, Unix, UTC
    @SerializedName("timezone") val timezone: Long,
) {
    fun toDomain(): Weather {
        return Weather(
            cityName = name,
            temperature = main.temp,
            feelsLike = main.feelsLike,
            minTemp = main.tempMin,
            maxTemp = main.tempMax,
            pressure = main.pressure,
            humidity = main.humidity,
            condition = weather.firstOrNull()?.main ?: "N/A",
            description = weather.firstOrNull()?.description ?: "N/A",
            icon = weather.firstOrNull()?.icon ?: "02d",
            windSpeed = wind.speed,
            sunrise = sys.sunrise,
            sunset = sys.sunset,
            lat = coord.lat,
            lon = coord.lon,
            dataTimestamp = System.currentTimeMillis(),
        )
    }
}

data class Coord(
    @SerializedName("lon") val lon: Double,
    @SerializedName("lat") val lat: Double,
)

data class WeatherDetail(
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String, // e.g., "Clouds"
    @SerializedName("description") val description: String, // e.g., "broken clouds"
    @SerializedName("icon") val icon: String, // e.g., "04n"
)

data class Main(
    @SerializedName("temp") val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
)

data class Wind(
    @SerializedName("speed") val speed: Double,
    @SerializedName("deg") val deg: Int,
)

data class Sys(
    @SerializedName("type") val type: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("country") val country: String,
    @SerializedName("sunrise") val sunrise: Long, // Unix, UTC
    @SerializedName("sunset") val sunset: Long, // Unix, UTC
)