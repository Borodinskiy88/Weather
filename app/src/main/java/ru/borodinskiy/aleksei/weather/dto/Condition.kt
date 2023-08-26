package ru.borodinskiy.aleksei.weather.dto

import com.google.gson.annotations.SerializedName

data class Condition(
    @SerializedName("icon")
    val icon: String,
    @SerializedName("text")
    val condition: String,
)
