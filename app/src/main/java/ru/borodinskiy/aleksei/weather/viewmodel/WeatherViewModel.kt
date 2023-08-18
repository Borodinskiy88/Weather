package ru.borodinskiy.aleksei.weather.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.borodinskiy.aleksei.weather.repository.WeatherRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepositoryImpl
) : ViewModel()