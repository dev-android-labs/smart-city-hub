package com.persistent.android.sujeet.smartcityhub.workers

import android.content.Context
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class WorkerUtility {

    private val requestNewsPeriodicSync =
        PeriodicWorkRequestBuilder<WeatherSyncWorker>(15, TimeUnit.MINUTES)
            .setConstraints(
                Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
            )
            .setInitialDelay(1, TimeUnit.MINUTES)
            .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 10, TimeUnit.SECONDS)
            .build()

    private val requestAQIPeriodicSync =
        PeriodicWorkRequestBuilder<AQISyncWorker>(15, TimeUnit.MINUTES)
            .setConstraints(
                Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
            )
            .setInitialDelay(2, TimeUnit.MINUTES)
            .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 10, TimeUnit.SECONDS)
            .build()


    //    methods for registering workers // call it from Application/MainActivity class
    fun registerWeatherSyncWorker(context: Context) {

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "weather_sync",
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE, requestNewsPeriodicSync
        )
    }


    fun registerAQISyncWorker(context: Context) {

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "aqi_sync",
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE, requestAQIPeriodicSync
        )
    }
}