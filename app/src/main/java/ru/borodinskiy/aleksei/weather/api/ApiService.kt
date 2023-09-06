package ru.borodinskiy.aleksei.weather.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.borodinskiy.aleksei.weather.api.ApiModule.Companion.API_KEY
import ru.borodinskiy.aleksei.weather.dto.Weather

interface ApiService {

    @GET("forecast.json")
    suspend fun getWeatherMoscow(
        @Query("key") key: String = API_KEY,
        @Query("days") days: Int = 5,
        @Query("q") q: String = "Moscow",
        @Query("lang") lang: String = "RU"
    ): Weather

}