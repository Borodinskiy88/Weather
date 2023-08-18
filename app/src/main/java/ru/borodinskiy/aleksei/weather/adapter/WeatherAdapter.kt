package ru.borodinskiy.aleksei.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.borodinskiy.aleksei.weather.R
import ru.borodinskiy.aleksei.weather.databinding.CardWeatherBinding
import ru.borodinskiy.aleksei.weather.dto.Weather

class WeatherAdapter() :
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
                when (weather.condition) {
                    "?" -> {
                        conditionIcon.setImageResource(R.drawable.ic_rain_24)
                    }

                    "?" -> {
                        conditionIcon.setImageResource(R.drawable.ic_snow_24)
                    }

                    "?" -> {
                        conditionIcon.setImageResource(R.drawable.ic_cloudy_24)
                    }

                    else -> conditionIcon.setImageResource(R.drawable.ic_sun_24)
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