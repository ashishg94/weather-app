package com.azaman.apps.weatherapp.Repository

import com.azaman.apps.weatherapp.api.ApiServiceImpl
import com.azaman.apps.weatherapp.model.CityData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class WeatherRepository @Inject constructor(private val apiServiceImpl: ApiServiceImpl) {

    fun getCityData(city: String): Flow<CityData> = flow {

        val response = apiServiceImpl.getCityData(city,"d1c335186561ddccc9dfe4899b3b820b")
        emit(response)
    }.flowOn(Dispatchers.IO)
        .conflate()

}


