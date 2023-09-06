package ru.borodinskiy.aleksei.weather.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.borodinskiy.aleksei.weather.dto.Weather
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val repository: WeatherRepository
) {

    fun getWeatherMoscow(): Flow<Weather> = flow {
        val response = repository.getWeatherMoscow()
        emit(response)
    }.flowOn(Dispatchers.IO)

}