package com.persistent.android.sujeet.smartcityhub.data

/**
 * Created by SUJEET KUMAR on 7/21/2025
 */
sealed class Result<T>() {

    data class SUCCESS<T>(val data: T) : Result<T>()
    data class ERROR<T>(val message: String) : Result<T>()
}