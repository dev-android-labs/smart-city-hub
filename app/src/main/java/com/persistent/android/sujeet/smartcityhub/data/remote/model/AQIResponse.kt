package com.persistent.android.sujeet.smartcityhub.data.remote.model

import com.google.gson.annotations.SerializedName
import com.persistent.android.sujeet.smartcityhub.domain.model.AirQuality
import com.persistent.android.sujeet.smartcityhub.domain.model.Component

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
data class AQIResponse(
    @SerializedName("coord") var coord: Coord,
    @SerializedName("list") var list: List<AqiList> = arrayListOf(),

    ) {
    fun toDomain(): AirQuality {
        return AirQuality(
            aqi = list.first().main.aqi,
            component = list.firstOrNull()?.components ?: Component()
        )
    }
}

data class AqiList(
    @SerializedName("main") var main: AqiMain,
    @SerializedName("components") var components: Component,
    @SerializedName("dt") var dt: Int,
)

data class AqiMain(
    @SerializedName("aqi") val aqi: Int,
)