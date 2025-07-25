package com.persistent.android.sujeet.smartcityhub.domain.usecases

import com.persistent.android.sujeet.smartcityhub.data.Result
import com.persistent.android.sujeet.smartcityhub.domain.model.City
import com.persistent.android.sujeet.smartcityhub.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by SUJEET KUMAR on 7/21/2025
 */
class SetCityUseCase @Inject constructor(private val cityRepository: CityRepository) {

    operator fun invoke(city: City): Flow<Result<City>> = cityRepository.selectCity(city)
}