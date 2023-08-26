package ru.borodinskiy.aleksei.weather.dto

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("name")
    val city: String,
    @SerializedName("country")
    val country: String,
)