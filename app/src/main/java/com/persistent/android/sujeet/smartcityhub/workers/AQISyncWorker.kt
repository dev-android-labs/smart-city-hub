package com.persistent.android.sujeet.smartcityhub.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.persistent.android.sujeet.smartcityhub.data.Result.ERROR
import com.persistent.android.sujeet.smartcityhub.data.Result.SUCCESS
import com.persistent.android.sujeet.smartcityhub.data.local.AppDataStorage
import com.persistent.android.sujeet.smartcityhub.data.remote.model.Coord
import com.persistent.android.sujeet.smartcityhub.domain.model.AirQuality
import com.persistent.android.sujeet.smartcityhub.domain.model.City
import com.persistent.android.sujeet.smartcityhub.domain.usecases.GetAirQualityUseCase
import com.persistent.android.sujeet.smartcityhub.domain.usecases.SetCityUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

/**
 * Created by SUJEET KUMAR on 7/21/2025
 */

@HiltWorker
class AQISyncWorker @AssistedInject constructor(
    // <-- Add @AssistedInject here
    @Assisted appContext: Context, // <-- Add @Assisted here
    @Assisted workerParams: WorkerParameters, // <-- Add @Assisted here
    private val getAirQualityUseCase: GetAirQualityUseCase,
    private val setCityUseCase: SetCityUseCase,
) : CoroutineWorker(appContext, workerParams) {

    val TAG = "AQISyncWorker"

    override suspend fun doWork(): Result {

        Log.d(TAG, "Worker Started...!")

        try {

            val result = setCityUseCase(City.BANGALORE)

            val coord = Coord(
                AppDataStorage.city.value.lat,
                AppDataStorage.city.value.lon
            )

            getAirQualityUseCase(
                coord.lat, coord.lon
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