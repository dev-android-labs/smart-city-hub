package com.persistent.android.sujeet.smartcityhub.presentation.weather

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.persistent.android.sujeet.smartcityhub.data.Result
import com.persistent.android.sujeet.smartcityhub.domain.model.City
import com.persistent.android.sujeet.smartcityhub.domain.model.Forecast
import com.persistent.android.sujeet.smartcityhub.domain.model.Weather
import com.persistent.android.sujeet.smartcityhub.domain.usecases.GetCityUseCase
import com.persistent.android.sujeet.smartcityhub.domain.usecases.GetCurrentWeatherUseCase
import com.persistent.android.sujeet.smartcityhub.domain.usecases.GetWeatherForecastUseCase
import com.persistent.android.sujeet.smartcityhub.presentation.home.ViewEffects
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getForecastUseCase: GetWeatherForecastUseCase,
    private val getCityUseCase: GetCityUseCase,
) : ViewModel() {

    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d("TAG", "coroutineExceptionHandler: ${throwable.message}")
            uiState.update {
                it.copy(error = throwable.message, isLoading = false)
            }
        }

    var uiState = MutableStateFlow(WeatherUiState())
        private set

    val viewEffects = MutableSharedFlow<ViewEffects>()


    val city = getCityUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            null
        )

    init {
        viewModelScope.launch {
            city.collect { city ->
                if (city != null) {
                    getCurrentWeather(city)
                    getForecast(city)
                }
            }
        }

    }

    fun onIntent(intent: WeatherEvent) {

        Log.d("TAG", "onIntent: ${intent.toString()}")

        when (intent) {
            is WeatherEvent.Refresh -> {
                getCurrentWeather(intent.city)
                getForecast(intent.city)
            }

            is WeatherEvent.RefreshWeather -> {
                getCurrentWeather(intent.city)
            }

            is WeatherEvent.RefreshForecast -> {
                getForecast(city = intent.city)
            }

            WeatherEvent.BackClicked -> {
                viewModelScope.launch {
                    viewEffects.emit(ViewEffects.NavigateBack)
                }
            }
        }
    }

    fun getCurrentWeather(city: City) {

        uiState.value = uiState.value.copy(city = city, isLoading = true)

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            getCurrentWeatherUseCase(city).collect { result ->
                when (result) {
                    is Result.ERROR<*> -> uiState.update {
                        it.copy(error = result.message, isLoading = false)
                    }

                    is Result.SUCCESS<Weather> -> uiState.update {
                        it.copy(weather = result.data, isLoading = false, error = null)
                    }
                }
            }
        }
    }


    fun getForecast(city: City) {
        uiState.value = uiState.value.copy(city = city, isLoading = true)

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            getForecastUseCase(city).collect { result ->
                when (result) {
                    is Result.ERROR<*> -> uiState.update {
                        it.copy(error = result.message, isLoading = false)
                    }

                    is Result.SUCCESS<List<Forecast>> -> uiState.update {
                        it.copy(forecasts = result.data, isLoading = false, error = null)
                    }
                }
            }

        }
    }
}