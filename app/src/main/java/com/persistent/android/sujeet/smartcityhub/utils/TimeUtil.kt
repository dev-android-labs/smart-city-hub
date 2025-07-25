package com.persistent.android.sujeet.smartcityhub.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Created by SUJEET KUMAR on 7/21/2025
 */
object TimeUtil {

    fun format(timestamp: Long): String {
        return SimpleDateFormat(
            FORMAT.DEFAULT.pattern,
            Locale.getDefault()
        ).format(Date(timestamp * 1000))
    }

    fun format(timestamp: Long, format: FORMAT): String {
        return SimpleDateFormat(format.pattern, Locale.getDefault()).format(Date(timestamp * 1000))
    }

}

enum class FORMAT(val pattern: String) {
    DEFAULT("dd-MMM-yyyy hh:mm a"),
    TIMESTAMP("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
    EEE_MMM_D("EEE, MMM d"),
    HH_MM("HH:mm"),
    DEFAULT_TIMESTAMP("EEE, MMM d HH:mm"),
}
