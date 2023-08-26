package ru.borodinskiy.aleksei.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.borodinskiy.aleksei.weather.dto.Weather
import ru.borodinskiy.aleksei.weather.repository.WeatherRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepositoryImpl
) : ViewModel() {

    val dataWeather = MutableLiveData<Weather>()
    val dataWeatherList = MutableLiveData<List<Weather>>()

    val data = getWeatherMoscow()

    fun loadWeather() {
        viewModelScope.launch {
            repository.getWeatherMoscow()
        }
    }

    fun getWeatherMoscow(): LiveData<List<Weather>> {
        return liveData {
            val data = repository.getWeatherMoscow()
            data.body()?.let { emit(listOf(it)) }
        }
    }

}