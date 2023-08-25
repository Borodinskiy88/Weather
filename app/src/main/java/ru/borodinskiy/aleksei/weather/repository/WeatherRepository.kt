package ru.borodinskiy.aleksei.weather.repository

import retrofit2.Response
import ru.borodinskiy.aleksei.weather.dto.Weather

interface WeatherRepository {

    suspend fun getWeatherMoscow(): Response<Weather>

}