package com.azaman.apps.weatherapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azaman.apps.weatherapp.Repository.WeatherRepository
import com.azaman.apps.weatherapp.model.CityData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private  val weatherRepository: WeatherRepository): ViewModel() {

    private val searchCity:MutableStateFlow<String?> = MutableStateFlow(null)

    private var searchJob:Job?=null

    init {

        searchCity.filterNotNull().onEach { city->
            searchJob?.cancel()
            searchJob = ticker(30000 , initialDelayMillis = 0)
                .receiveAsFlow()
                .onEach {
                    weatherRepository.getCityData(city).collect {
                        _cityData.postValue(it)
                    }
                }.launchIn(viewModelScope)

        }.launchIn(viewModelScope)

    }

    private val _cityData:MutableLiveData<CityData> = MutableLiveData()
    val cityData:LiveData<CityData> = _cityData

    fun getSearchData(city:String) {
        searchCity.value = city

    }
}
