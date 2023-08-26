package ru.borodinskiy.aleksei.weather.dto

import com.google.gson.annotations.SerializedName

data class ForecastDay(
    @SerializedName("day")
    val day: Day,
    @SerializedName("date")
    val date: String
)