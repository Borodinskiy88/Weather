package ru.borodinskiy.aleksei.weather.dto

import com.google.gson.annotations.SerializedName

data class Day(
    @SerializedName("avgtemp_c")
    val temperature: String,
    @SerializedName("maxwind_kph")
    val wind: String,
    @SerializedName("avghumidity")
    val humidity: String,
    @SerializedName("condition")
    val condition: Condition
)