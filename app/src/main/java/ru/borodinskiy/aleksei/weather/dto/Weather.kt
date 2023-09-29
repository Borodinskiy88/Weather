package ru.borodinskiy.aleksei.weather.dto

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("location")
    val location: Location,
    @SerializedName("forecast")
    val forecast: Forecast,
    @SerializedName("current")
    val current: Current,
)

