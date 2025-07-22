package com.persistent.android.sujeet.smartcityhub

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.persistent.android.sujeet.smartcityhub.data.local.AppDataStorage
import com.persistent.android.sujeet.smartcityhub.utils.JsonLoader
import com.persistent.android.sujeet.smartcityhub.workers.WorkerUtility
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */

@HiltAndroidApp
class SmartCityApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()


        val weatherResponse = JsonLoader.loadWeatherResponseFromJson(applicationContext)
        val aqiResponse = JsonLoader.loadAqiResponseFromJson(applicationContext)

        AppDataStorage.weatherResponse.value = weatherResponse
        AppDataStorage.aqiResponse.value = aqiResponse

        WorkerUtility().registerAQISyncWorker(applicationContext)
        WorkerUtility().registerWeatherSyncWorker(applicationContext)
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}