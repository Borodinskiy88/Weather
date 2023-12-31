package ru.borodinskiy.aleksei.weather.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import ru.borodinskiy.aleksei.weather.dto.Weather
import ru.borodinskiy.aleksei.weather.repository.WeatherRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepositoryImpl
) : ViewModel() {

    fun getWeather(city: String): LiveData<Weather> = repository.getWeatherMoscow(city)
        .catch { exception -> Log.d("asd", "Exception ${exception.message}") }
        .asLiveData()


}