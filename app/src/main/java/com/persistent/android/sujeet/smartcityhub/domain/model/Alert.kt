package com.persistent.android.sujeet.smartcityhub.domain.model

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
data class Alert(
    val title: String,
    val description: String,
    val alertType: AlertType = AlertType.INFO,
)

enum class AlertType{
    INFO,
    WARNING,
    ERROR,
}
