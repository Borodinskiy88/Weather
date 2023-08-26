package ru.borodinskiy.aleksei.weather.dto

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("location")
    val location: Location,
    @SerializedName("forecast")
    val forecast: Forecast
////    val id: Int,
//    val city: String,
//    val country: String,
//    val day: String,
//    val icon: String,
//    val condition: String,
//    val temperature: String,
//    val wind: String,
//    val humidity: String
)

