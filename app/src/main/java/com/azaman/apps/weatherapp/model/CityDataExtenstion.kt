package com.azaman.apps.weatherapp.model


fun Main.getTemperature():String
{
    return (this.temperature-273.15).toInt().toString()
}