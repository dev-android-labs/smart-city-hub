package com.persistent.android.sujeet.smartcityhub.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
data class City(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("coord") val coord: Coord,
    @SerializedName("country") val country: String,
    @SerializedName("population") val population: Long,
    @SerializedName("timezone") val timezone: Long,
    @SerializedName("sunrise") val sunrise: Long,
    @SerializedName("sunset") val sunset: Long
)