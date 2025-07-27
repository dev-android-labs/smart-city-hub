package com.persistent.android.sujeet.smartcityhub.domain.model

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
data class AirQuality(
    val aqi: Int,
    val component: Component = Component(),
) {

    fun getLevel(): Level {
        return Level.entries.find { it.value == aqi } ?: Level.GOOD
    }

    fun getInfo(): String {
        return when (aqi) {
            1 -> "Good"
            2 -> "Fair"
            3 -> "Moderate"
            4 -> "Poor"
            5 -> "Very Poor"
            else -> "Unknown"
        }
    }

    enum class Level(val value: Int, val level: String, val description: String) {
        GOOD(1, "Good", "Air quality is satisfactory, and air pollution poses little or no risk."),
        FAIR(
            2,
            "Fair",
            "Air quality is acceptable. However, there may be a risk for some people, particularly those who are unusually sensitive to air pollution."
        ),
        MODERATE(
            3,
            "Moderate",
            "Members of sensitive groups may experience health effects. The general public is less likely to be affected."
        ),
        POOR(
            4,
            "Poor",
            "Some members of the general public may experience health effects; members of sensitive groups may experience more serious health effects."
        ),
        VERY_POOR(
            5,
            "Very Poor",
            "Health alert: The risk of health effects is increased for everyone."
        );
    }

}

data class Component(
    val co: Double = 0.0,
    val no: Double = 0.0,
    val no2: Double = 0.0,
    val o3: Double = 0.0,
    val so2: Double = 0.0,
    val pm2_5: Double = 0.0,
    val pm10: Double = 0.0,
    val nh3: Double = 0.0,
)


