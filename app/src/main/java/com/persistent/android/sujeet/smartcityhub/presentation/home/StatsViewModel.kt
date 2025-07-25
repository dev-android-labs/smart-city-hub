package com.persistent.android.sujeet.smartcityhub.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.persistent.android.sujeet.smartcityhub.data.Result
import com.persistent.android.sujeet.smartcityhub.domain.model.AirQuality
import com.persistent.android.sujeet.smartcityhub.domain.model.City
import com.persistent.android.sujeet.smartcityhub.domain.model.Service
import com.persistent.android.sujeet.smartcityhub.domain.model.Weather
import com.persistent.android.sujeet.smartcityhub.domain.usecases.GetAirQualityUseCase
import com.persistent.android.sujeet.smartcityhub.domain.usecases.GetCityUseCase
import com.persistent.android.sujeet.smartcityhub.domain.usecases.GetCurrentWeatherUseCase
import com.persistent.android.sujeet.smartcityhub.domain.usecases.SetCityUseCase
import com.persistent.android.sujeet.smartcityhub.presentation.routes.AppEvent
import com.persistent.android.sujeet.smartcityhub.presentation.routes.ViewEffects
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
@HiltViewModel
class StatsViewModel @Inject constructor(
    val getWeatherUseCase: GetCurrentWeatherUseCase,
    val getAirQualityUseCase: GetAirQualityUseCase,
    val setCityUseCase: SetCityUseCase,
    val getCityUseCase: GetCityUseCase,
) : ViewModel() {

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("TAG", ": ${throwable.message}")
    }

    val uiState = MutableStateFlow(StatsUiState())

    var viewEffects = MutableSharedFlow<ViewEffects>()
        private set

    init {
        viewModelScope.launch {
            getCityUseCase().collect {
                uiState.value = uiState.value.copy(city = it)
                refreshData(it)
            }
        }

//        Keep updating the data every 1 minute
        viewModelScope.launch {
            while (isActive) {
                delay(1000 * 30 * 1)
                refreshData(city = uiState.value.city)
            }
        }


    }


    fun onIntent(event: AppEvent) {

        when (event) {

            AppEvent.SettingClicked -> {
                uiState.value = uiState.value.copy(showCitySelectionDialog = true)
            }

            is AppEvent.Refresh -> {
                refreshData(uiState.value.city)
            }

            AppEvent.HelpClicked -> {
                viewModelScope.launch {
                    viewEffects.emit(ViewEffects.ShowToast("Clicked ${event.toString()}"))
                }
            }

            AppEvent.CityDialogDismiss -> {
                uiState.value = uiState.value.copy(showCitySelectionDialog = false)
            }

            is AppEvent.CityChanged -> {

                setCityUseCase(event.city).launchIn(viewModelScope + coroutineExceptionHandler)

                uiState.update {
                    it.copy(showCitySelectionDialog = false)
                }
            }

            is AppEvent.WeatherClicked -> {
                viewModelScope.launch {
                    uiState.value.weather?.let {
                        viewEffects.emit(
                            ViewEffects.NavigateToWeatherScreen(
                                it
                            )
                        )
                    }
                }
            }

            is AppEvent.AqiClicked -> {
                getAqi(uiState.value.city)

                viewModelScope.launch {
                    uiState.value.aqi?.let { viewEffects.emit(ViewEffects.NavigateToAQIScreen(it)) }
                }
            }

            is AppEvent.TrafficClicked -> {
                viewModelScope.launch {
                    uiState.value.aqi?.let { viewEffects.emit(ViewEffects.NavigateToAQIScreen(it)) }
                }

            }

            is AppEvent.ServiceClicked -> {
                when (event.service) {
                    Service.PUBLIC_TRANSPORT -> {

                    }

                    Service.EMERGENCY_SERVICES -> {
                    }

                    Service.WASTE_MANAGEMENT -> {

                    }

                    Service.CITY_EVENTS -> {

                    }
                }

                viewModelScope.launch {
                    viewEffects.emit(ViewEffects.ShowToast("Service Clicked ${event.service.name}"))
                }
            }

            is AppEvent.AlertClicked -> {
                viewModelScope.launch {
                    viewEffects.emit(ViewEffects.NavigateToAlertScreen(event.alert))
                }
            }

            else -> {}
        }
    }

    fun getWeather(city: City) {

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            getWeatherUseCase(city).collect { result ->
                when (result) {
                    is Result.ERROR<*> -> uiState.value = uiState.value.copy(error = result.message)
                    is Result.SUCCESS<Weather> -> uiState.value =
                        uiState.value.copy(weather = result.data)
                }
            }
        }
    }

    fun getAqi(city: City) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            getAirQualityUseCase(city).collect { result ->
                when (result) {
                    is Result.ERROR<*> -> {
                        uiState.value = uiState.value.copy(error = result.message)
                    }

                    is Result.SUCCESS<AirQuality> -> uiState.value = uiState.value.copy(
                        aqi = result.data
                    )
                }
            }
        }
    }

    fun refreshData(city: City) {
        getWeather(city)
        getAqi(city)
    }
}