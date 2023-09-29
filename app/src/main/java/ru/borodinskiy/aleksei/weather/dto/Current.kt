package ru.borodinskiy.aleksei.weather.dto

import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("condition")
    val condition: Condition,
    @SerializedName("is_day")
    val isDay: Int,
    @SerializedName("temp_c")
    val tempCurrent: Double,
)
