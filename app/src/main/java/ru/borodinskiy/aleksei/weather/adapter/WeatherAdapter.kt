package ru.borodinskiy.aleksei.weather.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.borodinskiy.aleksei.weather.databinding.CardWeatherBinding
import ru.borodinskiy.aleksei.weather.dto.ForecastDay
import ru.borodinskiy.aleksei.weather.util.load
import ru.borodinskiy.aleksei.weather.utils.ReformatValues.reformatDate

class WeatherAdapter(
    private val forecastDay: List<ForecastDay>,
) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    private lateinit var _context: Context

    inner class WeatherViewHolder(
        private val binding: CardWeatherBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        fun bind(forecastDay: ForecastDay) {
            binding.apply {

                //Todo Температура выбрана максимальная, а не средняя
                day.text = reformatDate(forecastDay.date)
                conditionText.text = forecastDay.day.condition.condition
                temp.text = forecastDay.day.temperatureMax.toInt().toString() + " °C"
                wind.text = forecastDay.day.wind.toInt().toString() + " км/ч"
                humidity.text = forecastDay.day.humidity.toInt().toString() + " %"
                conditionIcon.load(forecastDay.day.condition.icon)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = CardWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        _context = parent.context
        return WeatherViewHolder(binding)
    }

    override fun getItemCount(): Int = forecastDay.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(forecastDay[position])
    }
}

