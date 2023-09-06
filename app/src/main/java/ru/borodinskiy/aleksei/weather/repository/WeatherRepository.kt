package ru.borodinskiy.aleksei.weather.repository

import ru.borodinskiy.aleksei.weather.api.ApiService
import ru.borodinskiy.aleksei.weather.dto.Weather
import javax.inject.Inject

class WeatherRepository
@Inject constructor(private val apiServices: ApiService) {

    suspend fun getWeatherMoscow(): Weather = apiServices.getWeatherMoscow()

}