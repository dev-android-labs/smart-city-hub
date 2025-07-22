package com.persistent.android.sujeet.smartcityhub.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Created by SUJEET KUMAR on 7/21/2025
 */
object TimeUtil{

    fun formatTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("dd-MMM-yyyy hh:mm a", Locale.getDefault())
        return format.format(date)
    }
}
