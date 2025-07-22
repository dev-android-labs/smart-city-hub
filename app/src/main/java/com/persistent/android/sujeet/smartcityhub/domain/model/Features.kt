package com.persistent.android.sujeet.smartcityhub.domain.model

import com.persistent.android.sujeet.smartcityhub.R

/**
 * Created by SUJEET KUMAR on 7/21/2025
 */

enum class Stats {
    WEATHER,
    AQI,
    TRAFFIC
}

enum class Service(val resId: Int, val serviceName: String) {

    PUBLIC_TRANSPORT(R.drawable.icons8_bus_48, "Public Transport"),
    EMERGENCY_SERVICES(R.drawable.icons8_emergency_48, "Emergency Services"),
    WASTE_MANAGEMENT(R.drawable.icons8_waste_50, "Waste Management"),
    CITY_EVENTS(R.drawable.icons8_event_50, "City Events");
}

enum class Alerts {
    WEATHER,
    AQI,
    TRAFFIC
}

