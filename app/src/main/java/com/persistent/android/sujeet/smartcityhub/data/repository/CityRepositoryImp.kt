package com.persistent.android.sujeet.smartcityhub.data.repository

import com.persistent.android.sujeet.smartcityhub.data.Result
import com.persistent.android.sujeet.smartcityhub.data.local.AppDataStorage
import com.persistent.android.sujeet.smartcityhub.domain.model.City
import com.persistent.android.sujeet.smartcityhub.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by SUJEET KUMAR on 7/21/2025
 */
class CityRepositoryImp @Inject constructor(val appStorage: AppDataStorage) : CityRepository {
    override fun getCity(): Flow<City> {
        return appStorage.city
    }

    override fun selectCity(city: City): Flow<Result<City>> = flow {
        appStorage.city.value = city
        emit(Result.SUCCESS(city))
    }

    override suspend fun getCities(): Flow<List<City>> {
        return flow {
            emit(AppDataStorage.cities)
        }
    }
}