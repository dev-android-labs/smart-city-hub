package com.persistent.android.sujeet.smartcityhub.data.local

import com.persistent.android.sujeet.smartcityhub.data.remote.model.AQIResponse
import com.persistent.android.sujeet.smartcityhub.data.remote.model.Coord
import com.persistent.android.sujeet.smartcityhub.data.remote.model.Main
import com.persistent.android.sujeet.smartcityhub.data.remote.model.Sys
import com.persistent.android.sujeet.smartcityhub.data.remote.model.WeatherDetail
import com.persistent.android.sujeet.smartcityhub.data.remote.model.WeatherResponse
import com.persistent.android.sujeet.smartcityhub.data.remote.model.Wind
import com.persistent.android.sujeet.smartcityhub.domain.model.City
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Created by SUJEET KUMAR on 7/21/2025
 */
object AppDataStorage {

    val cities = City.entries.toList()

    var city = MutableStateFlow(City.BANGALORE)

    var weatherResponse = MutableStateFlow<WeatherResponse>(
        WeatherResponse(
            coord = Coord(city.value.lat, city.value.lon),
            weather = listOf(
                WeatherDetail(
                    id = 1,
                    main = "na",
                    description = "",
                    icon = "02"
                )
            ),
            main = Main(
                temp = 1.1,
                feelsLike = 1.1,
                tempMin = 1.1,
                tempMax = 1.0,
                pressure = 1,
                humidity = 1
            ),
            wind = Wind(speed = 22.0, deg = 1),
            sys = Sys(type = 1, id = 1, country = "India", sunrise = 1, sunset = 1),
            name = city.value.cityName,
            dt =  1,
            timezone = 22345
        )
    )

    var aqiResponse = MutableStateFlow<AQIResponse>(
        AQIResponse(
            coord = Coord(city.value.lat, city.value.lon),
            list = listOf()
        )
    )

}