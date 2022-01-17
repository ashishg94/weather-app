package com.azaman.apps.weatherapp.api

import com.azaman.apps.weatherapp.model.CityData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/data/2.5/weather")
    suspend fun getCityData(
        @Query("q")q:String,
        @Query("appid")appId:String
    ):CityData

}