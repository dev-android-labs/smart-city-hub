package com.persistent.android.sujeet.smartcityhub.presentation.aqi

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.persistent.android.sujeet.smartcityhub.data.Result
import com.persistent.android.sujeet.smartcityhub.domain.model.AirQuality
import com.persistent.android.sujeet.smartcityhub.domain.usecases.GetAirQualityUseCase
import com.persistent.android.sujeet.smartcityhub.domain.usecases.GetCityUseCase
import com.persistent.android.sujeet.smartcityhub.presentation.routes.AppEvent
import com.persistent.android.sujeet.smartcityhub.presentation.routes.ViewEffects
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by SUJEET KUMAR on 7/26/2025
 */

@HiltViewModel
class AirQualityViewModel @Inject constructor(
    private val getCityUseCase: GetCityUseCase,
    private val getAirQualityUseCase: GetAirQualityUseCase,
) : ViewModel() {

    val uiState = MutableStateFlow(AirQualityUiState())

    val viewEffects = MutableSharedFlow<ViewEffects>()


    fun onIntent(event: AppEvent) {
        Log.d("TAG", "onIntent: $event")
        when (event) {
            is AppEvent.BackClicked -> {
                viewModelScope.launch {
                    viewEffects.emit(ViewEffects.NavigateBack)
                }
            }

            is AppEvent.ActionRefreshClicked -> {
                getAirQuality()
            }

            is AppEvent.ActionSettingClicked -> {
                uiState.update {
                    it.copy(showCitySelectionDialog = true)
                }
            }

            is AppEvent.CityDialogDismiss -> {
                uiState.update { it.copy(showCitySelectionDialog = false) }
            }

            is AppEvent.CityChanged -> {
                uiState.update { it.copy(city = event.city, showCitySelectionDialog = false) }
                getAirQuality()
            }

            else -> {

            }
        }
    }

    init {
        viewModelScope.launch {
            getCityUseCase().collect { city ->
                uiState.value = uiState.value.copy(city = city)
                getAirQuality()
            }
        }
    }


    fun getAirQuality() {
        uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            getAirQualityUseCase(uiState.value.city).collect { result ->
                when (result) {
                    is Result.ERROR<*> -> uiState.update { it.copy(error = result.message) }
                    is Result.SUCCESS<AirQuality> -> uiState.update {
                        AirQualityUiState(aqi = result.data, city = it.city)
                    }
                }

                uiState.update { it.copy(isLoading = false) }
            }
        }
    }

}