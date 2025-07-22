package com.persistent.android.sujeet.smartcityhub.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.persistent.android.sujeet.smartcityhub.data.Result.ERROR
import com.persistent.android.sujeet.smartcityhub.data.Result.SUCCESS
import com.persistent.android.sujeet.smartcityhub.data.local.AppDataStorage
import com.persistent.android.sujeet.smartcityhub.domain.model.Weather
import com.persistent.android.sujeet.smartcityhub.domain.usecases.GetCurrentWeatherUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

/**
 * Created by SUJEET KUMAR on 7/21/2025
 */

@HiltWorker
class WeatherSyncWorker @AssistedInject constructor(
    // <-- Add @AssistedInject here
    @Assisted appContext: Context, // <-- Add @Assisted here
    @Assisted workerParams: WorkerParameters, // <-- Add @Assisted here
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
) : CoroutineWorker(appContext, workerParams) {

    val TAG = "WeatherSyncWorker"

    override suspend fun doWork(): Result {

        Log.d(TAG, "Worker Started...!")

        try {
            getCurrentWeatherUseCase(
                AppDataStorage.city.value.cityName
            ).collect { result ->

                when (result) {
                    is ERROR<*> -> Result.failure()
                    is SUCCESS<Weather> -> Result.success()
                }
            }

            return Result.success()

        } catch (e: Exception) {

            Log.e(TAG, "doWork: ${e.message}")
            return if (runAttemptCount < 3) {
                Result.retry()
            } else {
                Result.failure()
            }
        }
    }
}