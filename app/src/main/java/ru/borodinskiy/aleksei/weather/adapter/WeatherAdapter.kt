package ru.borodinskiy.aleksei.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.borodinskiy.aleksei.weather.databinding.CardWeatherBinding
import ru.borodinskiy.aleksei.weather.dto.Weather
import ru.borodinskiy.aleksei.weather.util.load

class WeatherAdapter :
    ListAdapter<Weather, WeatherAdapter.CarViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {

        return CarViewHolder(
            CardWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class CarViewHolder(
        private val binding: CardWeatherBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: Weather) {

            binding.apply {
                day.text = weather.day
                conditionText.text = weather.condition
                temp.text = weather.temperature.toString()
                wind.text = weather.wind.toString()
                humidity.text = weather.humidity.toString()
                weather.icon.let {
                    conditionIcon.load(it)
                }
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Weather>() {
            override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
                return oldItem == newItem
            }
        }
    }
}