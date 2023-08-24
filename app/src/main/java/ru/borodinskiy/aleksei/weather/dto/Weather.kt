package ru.borodinskiy.aleksei.weather.dto

data class Weather(
    val id: Int,
    val day: String,
    val icon: String,
    val condition: String,
    val temperature: Int,
    val wind: Int,
    val humidity: Int
)

data class WeatherResults(
    val results: List<Weather>
)
