package ru.borodinskiy.aleksei.weather.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.borodinskiy.aleksei.weather.dto.Weather
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiServiceImpl: ApiServiceImpl
) {

    fun getWeatherMoscow(city: String): Flow<Weather> = flow {
        val response = apiServiceImpl.getWeatherMoscow(city = city)
        emit(response)
    }.flowOn(Dispatchers.IO)

}