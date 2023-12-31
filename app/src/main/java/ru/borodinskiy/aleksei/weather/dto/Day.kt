package ru.borodinskiy.aleksei.weather.dto

import com.google.gson.annotations.SerializedName

data class Day(
    @SerializedName("avgtemp_c")
    val temperature: Double,
    @SerializedName("maxtemp_c")
    val temperatureMax: Double,
    @SerializedName("mintemp_c")
    val temperatureMin: Double,
    @SerializedName("maxwind_kph")
    val wind: Double,
    @SerializedName("avghumidity")
    val humidity: Double,
    @SerializedName("condition")
    val condition: Condition
)