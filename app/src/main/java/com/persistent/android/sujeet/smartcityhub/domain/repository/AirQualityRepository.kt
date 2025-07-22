package com.persistent.android.sujeet.smartcityhub.domain.repository

import com.persistent.android.sujeet.smartcityhub.data.Result
import com.persistent.android.sujeet.smartcityhub.domain.model.AirQuality
import com.persistent.android.sujeet.smartcityhub.domain.model.City
import kotlinx.coroutines.flow.Flow

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
interface AirQualityRepository {

    fun getAirQuality(): Flow<AirQuality>
    suspend fun getAirQuality(city: City): Flow<Result<AirQuality>>
}