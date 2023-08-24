package ru.borodinskiy.aleksei.weather.repository

import ru.borodinskiy.aleksei.weather.api.ApiService
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : WeatherRepository {

    override suspend fun getWeatherMoscow() = apiService.getWeather()

}