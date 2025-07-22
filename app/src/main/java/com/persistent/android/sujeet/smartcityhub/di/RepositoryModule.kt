package com.persistent.android.sujeet.smartcityhub.di

import com.persistent.android.sujeet.smartcityhub.data.local.AppDataStorage
import com.persistent.android.sujeet.smartcityhub.data.remote.AQIApiService
import com.persistent.android.sujeet.smartcityhub.data.remote.WeatherApiService
import com.persistent.android.sujeet.smartcityhub.data.repository.AirQualityRepositoryImp
import com.persistent.android.sujeet.smartcityhub.data.repository.CityRepositoryImp
import com.persistent.android.sujeet.smartcityhub.data.repository.WeatherRepositoryImp
import com.persistent.android.sujeet.smartcityhub.domain.repository.AirQualityRepository
import com.persistent.android.sujeet.smartcityhub.domain.repository.CityRepository
import com.persistent.android.sujeet.smartcityhub.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideWeatherRepository(
        apiService: WeatherApiService,
    ): WeatherRepository {
        return WeatherRepositoryImp(apiService)
    }

    @Provides
    fun provideAirQualityRepository(apiService: AQIApiService): AirQualityRepository {
        return AirQualityRepositoryImp(apiService)
    }

    @Provides
    fun provideCityRepository(appStorage: AppDataStorage): CityRepository {
        return CityRepositoryImp(appStorage)
    }

    @Provides
    fun provideAppStorage(): AppDataStorage {
        return AppDataStorage
    }
}