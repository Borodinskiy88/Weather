package ru.borodinskiy.aleksei.weather.dto

data class Weather(
//    val id: Int,
    val city: String,
    val country: String,
    val day: String,
    val icon: String,
    val condition: String,
    val temperature: String,
    val wind: String,
    val humidity: String
)

