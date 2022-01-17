package com.azaman.apps.weatherapp.model

import com.google.gson.annotations.SerializedName

data class CityData(
    @SerializedName("weather")
    val weather:List<Weather>,
    @SerializedName("main")
    val main:Main,
    @SerializedName("wind")
    val wind:Wind,
    @SerializedName("name")
    val name:String
)


data class Weather(
    @SerializedName("description")
    val description:String
)

data class Main(
    @SerializedName("temp")
    val temperature:Double,
    @SerializedName("humidity")
    val humidity:Int
)

data class Wind(
    @SerializedName("speed")
    val speed:Float,
)

