package com.persistent.android.sujeet.smartcityhub.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.persistent.android.sujeet.smartcityhub.data.Result.ERROR
import com.persistent.android.sujeet.smartcityhub.data.Result.SUCCESS
import com.persistent.android.sujeet.smartcityhub.domain.model.AirQuality
import com.persistent.android.sujeet.smartcityhub.domain.usecases.GetAirQualityUseCase
import com.persistent.android.sujeet.smartcityhub.domain.usecases.GetCityUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first

/**
 * Created by SUJEET KUMAR on 7/21/2025
 */

@HiltWorker
class AQISyncWorker @AssistedInject constructor(
    // <-- Add @AssistedInject here
    @Assisted appContext: Context, // <-- Add @Assisted here
    @Assisted workerParams: WorkerParameters, // <-- Add @Assisted here
    private val getAirQualityUseCase: GetAirQualityUseCase,
    private val getCityUseCase: GetCityUseCase,
) : CoroutineWorker(appContext, workerParams) {

    val TAG = "AQISyncWorker"

    override suspend fun doWork(): Result {

        Log.d(TAG, "Worker Started...!")

        try {

            getAirQualityUseCase(
                city = getCityUseCase().first()
            ).collect { result ->

                when (result) {
                    is ERROR<*> -> Result.failure()
                    is SUCCESS<AirQuality> -> Result.success()
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