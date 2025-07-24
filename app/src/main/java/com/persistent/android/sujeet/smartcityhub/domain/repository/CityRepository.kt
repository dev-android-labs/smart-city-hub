package com.persistent.android.sujeet.smartcityhub.domain.repository

import com.persistent.android.sujeet.smartcityhub.data.Result
import com.persistent.android.sujeet.smartcityhub.domain.model.City
import kotlinx.coroutines.flow.Flow

/**
 * Created by SUJEET KUMAR on 7/21/2025
 */
interface CityRepository {

    fun getCity(): Flow<City>
    fun selectCity(city: City): Flow<Result<City>>
    suspend fun getCities(): Flow<List<City>>
}