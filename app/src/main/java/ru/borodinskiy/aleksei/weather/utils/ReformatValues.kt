package ru.borodinskiy.aleksei.weather.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Locale

object ReformatValues {

    @SuppressLint("SimpleDateFormat")
    fun reformatTime(date: String): String {
        val timeObj = SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date)
        val currentTime =
            timeObj?.let { SimpleDateFormat("HH:mm", Locale("ru")).format(it) }
        return currentTime.toString()
    }

    @SuppressLint("SimpleDateFormat")
    fun reformatDate(date: String): String {
        val dateObj = SimpleDateFormat("yyyy-MM-dd").parse(date)
        val reformatDate = dateObj?.let { SimpleDateFormat("d  MMMM", Locale("ru")).format(it) }
        return reformatDate.toString()
    }

    fun reformatSpeedWind(speed: Int): Int {
        val ratio = 0.277778
        return (speed * ratio).toInt()
    }
}