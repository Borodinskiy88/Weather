package ru.borodinskiy.aleksei.weather.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.borodinskiy.aleksei.weather.databinding.CardWeatherBinding
import ru.borodinskiy.aleksei.weather.dto.Weather
import ru.borodinskiy.aleksei.weather.util.load

class WeatherAdapter :
    ListAdapter<Weather, WeatherAdapter.WeatherViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {

        return WeatherViewHolder(
            CardWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
//        getItem(position)?.let { holder.bind(it) }
        holder.bind(getItem(position))
    }

    class WeatherViewHolder(
        private val binding: CardWeatherBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(weather: Weather) {

            binding.apply {

                day.text = weather.forecast.forecastDay[0].date
                conditionText.text = weather.forecast.forecastDay[0].day.condition.condition
                temp.text = weather.forecast.forecastDay[0].day.temperature + " °C"
                wind.text = weather.forecast.forecastDay[0].day.wind + " км/ч"
                humidity.text = weather.forecast.forecastDay[0].day.humidity + " %"
                conditionIcon.load(weather.forecast.forecastDay[0].day.condition.icon)

//                day.text = weather.forecast.forecastDay[layoutPosition].date
//                conditionText.text = weather.forecast.forecastDay[layoutPosition].day.condition.condition
//                temp.text = weather.forecast.forecastDay[layoutPosition].day.temperature + " °C"
//                wind.text = weather.forecast.forecastDay[layoutPosition].day.wind + " км/ч"
//                humidity.text = weather.forecast.forecastDay[layoutPosition].day.humidity + " %"
//                conditionIcon.load(weather.forecast.forecastDay[layoutPosition].day.condition.icon)
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Weather>() {
            override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
                return oldItem == newItem
            }
        }
    }
}