package ru.borodinskiy.aleksei.weather.repository

import ru.borodinskiy.aleksei.weather.api.ApiService
import ru.borodinskiy.aleksei.weather.dto.Weather
import javax.inject.Inject

class ApiServiceImpl
@Inject constructor(private val apiServices: ApiService) {

    suspend fun getWeatherMoscow(city: String): Weather = apiServices.getWeatherMoscow(q = city)

}