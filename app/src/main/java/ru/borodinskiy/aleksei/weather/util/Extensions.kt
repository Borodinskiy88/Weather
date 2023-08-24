package ru.borodinskiy.aleksei.weather.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import ru.borodinskiy.aleksei.weather.R

fun ImageView.load(url: String, vararg transforms: BitmapTransformation = emptyArray()) =
    Glide.with(this)
        .load(url)
        .error(R.drawable.ic_error_24)
        .timeout(10_000)
        .transform(*transforms)
        .into(this)
