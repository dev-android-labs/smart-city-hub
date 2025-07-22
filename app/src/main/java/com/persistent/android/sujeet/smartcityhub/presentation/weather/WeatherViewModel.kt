package com.persistent.android.sujeet.smartcityhub.presentation.weather

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.persistent.android.sujeet.smartcityhub.domain.model.City
import com.persistent.android.sujeet.smartcityhub.domain.usecases.GetCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */

@HiltViewModel
class WeatherViewModel @Inject constructor(
    val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
) : ViewModel() {



    var uiState = MutableStateFlow(WeatherUiState())
        private set


    fun onIntent(intent: WeatherEvent) {

        Log.d("TAG", "onIntent: ${intent.toString()}")

        when (intent) {
            is WeatherEvent.LoadWeather -> {
                Log.d("TAG", "onIntent: ${intent.cityName}")
            }

            is WeatherEvent.RefreshWeather -> {
                Log.d("TAG", "onIntent: ${intent.cityName}")
            }
        }
    }

    init {

        viewModelScope.launch {

            val weather = getCurrentWeatherUseCase(City.BANGALORE.cityName).stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000), null
            )

        }

    }
}