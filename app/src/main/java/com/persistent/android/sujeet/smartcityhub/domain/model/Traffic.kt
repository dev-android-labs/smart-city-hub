package com.persistent.android.sujeet.smartcityhub.domain.model

/**
 * Created by SUJEET KUMAR on 7/21/2025
 */
data class Traffic(
    val speed: Long? = 0,
    val congestion: Congestion = Congestion.NORMAL,
)


enum class Congestion(val value: String) {
    NORMAL("Normal"),
    SLOW("Slow"),
    TRAFFIC("Traffic");

    companion object {
        fun fromValue(value: String): Congestion? {
            return Congestion.entries.find { it.value == value }
        }
    }
}
