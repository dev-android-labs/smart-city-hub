package com.persistent.android.sujeet.smartcityhub.utils

import android.content.Context
import com.google.gson.Gson
import com.persistent.android.sujeet.smartcityhub.data.remote.model.AQIResponse
import com.persistent.android.sujeet.smartcityhub.data.remote.model.WeatherResponse

/**
 * Created by SUJEET KUMAR on 7/21/2025
 */
object JsonLoader {

    fun loadWeatherResponseFromJson(context: Context): WeatherResponse {
        val jsonString = context.assets.open("weather_response.json").bufferedReader().use { it.readText() }
        return Gson().fromJson(jsonString, WeatherResponse::class.java)
    }

    fun loadAqiResponseFromJson(context: Context): AQIResponse{
        val jsonString = context.assets.open("aqi_response.json").bufferedReader().use { it.readText() }
        return Gson().fromJson(jsonString, AQIResponse::class.java)
    }
}