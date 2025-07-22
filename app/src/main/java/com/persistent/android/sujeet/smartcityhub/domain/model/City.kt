package com.persistent.android.sujeet.smartcityhub.domain.model

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
enum class City(val cityName: String, val lat: Double = 0.0, val lon: Double = 0.0) {

    MUMBAI("Mumbai", 19.07, 72.87),
    DELHI("Delhi", 28.61, 77.23),
    KOLKATA("Kolkata", 22.57, 88.36),
    CHENNAI("Chennai", 13.08, 80.28),
    BANGALORE("Bangalore", 12.97, 77.58),
    HYDERABAD("Hyderabad", 17.38, 78.48),
    LONDON("London", -0.1257, 51.5085);

    companion object {
        fun fromDisplayName(displayName: String): City? {
            return entries.find { it.cityName == displayName }
        }
    }
}