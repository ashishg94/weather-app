package com.azaman.apps.weatherapp.api

import com.azaman.apps.weatherapp.model.CityData
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService){

    suspend fun getCityData(city:String,appId:String):CityData=
        apiService.getCityData(city,appId)
}