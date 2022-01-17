package com.azaman.apps.weatherapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.azaman.apps.weatherapp.ViewModel.WeatherViewModel
import com.azaman.apps.weatherapp.databinding.ActivityMainBinding
import com.azaman.apps.weatherapp.model.getTemperature
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val weatherViewModel: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        init()
        weatherViewModel.cityData.observe(this) { response ->

            if(response.weather.isNotEmpty()) {
                val weather=response.weather[0]
                if (weather.description == "clear sky" || weather.description == "mist") {
                    Glide.with(this)
                        .load(R.drawable.clouds)
                        .into(binding.image)
                } else
                    if (weather.description == "haze" || weather.description == "overcast clouds" || weather.description == "fog") {
                        Glide.with(this)
                            .load(R.drawable.haze)
                            .into(binding.image)
                    } else
                        if (weather.description == "rain") {
                            Glide.with(this)
                                .load(R.drawable.rain)
                                .into(binding.image)
                        }
                binding.description.text = weather.description
            }
            binding.name.text = response.name
            binding.speed.text = response.wind.speed.toString()
            binding.temp.text = response.main.getTemperature()
            binding.humidity.text = response.main.humidity.toString()
        }
    }

    private fun init() {

        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { weatherViewModel.getSearchData(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }
}