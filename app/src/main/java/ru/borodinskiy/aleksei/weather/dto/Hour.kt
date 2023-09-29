package ru.borodinskiy.aleksei.weather.dto

import com.google.gson.annotations.SerializedName

data class Hour(
    @SerializedName("condition")
    val condition: Condition,
    @SerializedName("temp_c")
    val tempHour: Double,
    @SerializedName("time")
    val time: String
)
