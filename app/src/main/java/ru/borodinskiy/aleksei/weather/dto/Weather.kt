package ru.borodinskiy.aleksei.weather.dto

data class Weather(
    val id: Int,
    val condition: String,
    val temperature: Int,
    val wind: Int,
    val humidity: Int
)
