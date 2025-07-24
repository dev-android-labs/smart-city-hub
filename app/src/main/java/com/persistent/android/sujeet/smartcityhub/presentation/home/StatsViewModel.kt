package com.persistent.android.sujeet.smartcityhub.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.persistent.android.sujeet.smartcityhub.data.Result
import com.persistent.android.sujeet.smartcityhub.domain.model.AirQuality
import com.persistent.android.sujeet.smartcityhub.domain.model.City
import com.persistent.android.sujeet.smartcityhub.domain.model.Service
import com.persistent.android.sujeet.smartcityhub.domain.model.Weather
import com.persistent.android.sujeet.smartcityhub.domain.usecases.GetAirQualityUseCase
import com.persistent.android.sujeet.smartcityhub.domain.usecases.GetCurrentWeatherUseCase
import com.persistent.android.sujeet.smartcityhub.domain.usecases.SetCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
@HiltViewModel
class StatsViewModel @Inject constructor(
    val getWeatherUseCase: GetCurrentWeatherUseCase,
    val getAirQualityUseCase: GetAirQualityUseCase,
    val setCityUseCase: SetCityUseCase,
) : ViewModel() {

    private var _state = MutableStateFlow(StatsUiState())

    var city = MutableStateFlow(City.BANGALORE)

    var viewEffects = MutableSharedFlow<ViewEffects>()
        private set

    private val weather = getWeatherUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    private val aqi = getAirQualityUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)


    val uiState = combine(_state, city, weather, aqi) { state, city, weather, aqi ->
        state.copy(city = city, weather = weather, aqi = aqi)

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), StatsUiState())


    init {

    }


    fun onIntent(event: StatsEvent) {

        when (event) {

            StatsEvent.SettingClicked -> {
                _state.value = uiState.value.copy(showCitySelectionDialog = true)
            }

            is StatsEvent.Refresh -> {
                refreshData(uiState.value.city)
            }

            StatsEvent.HelpClicked -> {
                viewModelScope.launch {
                    viewEffects.emit(ViewEffects.ShowToast("Clicked ${event.toString()}"))
                }
            }

            StatsEvent.CityDialogDismiss -> {
                _state.value = uiState.value.copy(showCitySelectionDialog = false)
            }

            is StatsEvent.CitySelected -> {

                viewModelScope.launch {
                    setCityUseCase(event.city).first()
                }

                _state.value = uiState.value.copy(
                    city = event.city,
                    showCitySelectionDialog = false
                )
                getWeather(event.city)
                getAqi(event.city)
            }

            is StatsEvent.WeatherClicked -> {
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

            is StatsEvent.AqiClicked -> {
                getAqi(uiState.value.city)

                viewModelScope.launch {
                    uiState.value.aqi?.let { viewEffects.emit(ViewEffects.NavigateToAQIScreen(it)) }
                }
            }

            is StatsEvent.TrafficClicked -> {
                viewModelScope.launch {
                    uiState.value.aqi?.let { viewEffects.emit(ViewEffects.NavigateToAQIScreen(it)) }
                }

            }

            is StatsEvent.ServiceClicked -> {
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

            is StatsEvent.AlertClicked -> {
                viewModelScope.launch {
                    viewEffects.emit(ViewEffects.NavigateToAlertScreen(event.alert))
                }
            }

            else -> {}
        }
    }

    fun getWeather(city: City) {

        viewModelScope.launch {
            getWeatherUseCase(city).collect { result ->
                when (result) {
                    is Result.ERROR<*> -> _state.value = uiState.value.copy(error = result.message)
                    is Result.SUCCESS<Weather> -> _state.value =
                        uiState.value.copy(weather = result.data)
                }
            }
        }
    }

    fun getAqi(city: City) {
        viewModelScope.launch {
            getAirQualityUseCase(city).collect { result ->
                when (result) {
                    is Result.ERROR<*> -> _state.value = uiState.value.copy(error = result.message)
                    is Result.SUCCESS<AirQuality> -> _state.value = uiState.value.copy(
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