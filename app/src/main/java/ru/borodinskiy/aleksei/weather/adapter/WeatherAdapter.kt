package ru.borodinskiy.aleksei.weather.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.borodinskiy.aleksei.weather.databinding.CardWeatherBinding
import ru.borodinskiy.aleksei.weather.dto.ForecastDay
import ru.borodinskiy.aleksei.weather.util.load
import java.text.SimpleDateFormat
import java.util.Locale

interface OnInteractionListener {
    fun onShowDetail()
}

class WeatherAdapter(
    private val forecastDay: List<ForecastDay>,
    private val onInteractionListener: OnInteractionListener
) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    private lateinit var _context: Context

    inner class WeatherViewHolder(
        private val binding: CardWeatherBinding,
        private val onInteractionListener: OnInteractionListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        fun bind(forecastDay: ForecastDay) {
            binding.apply {


//                val formatter = DateTimeFormatter.ofPattern("dd-MM")
//                val date = LocalDate.parse(forecastDay.date)
//                val new = date.format(formatter)

                val dateString = forecastDay.date
                val dateObj = SimpleDateFormat("yyyy-MM-dd").parse(dateString)
                val date = dateObj?.let { SimpleDateFormat("d  MMMM", Locale("ru")).format(it) }

                //Todo Температура выбрана максимальная, а не средняя
                day.text = date
                conditionText.text = forecastDay.day.condition.condition
                temp.text = forecastDay.day.temperatureMax.toInt().toString() + " °C"
                wind.text = forecastDay.day.wind.toInt().toString() + " км/ч"
                humidity.text = forecastDay.day.humidity.toInt().toString() + " %"
                conditionIcon.load(forecastDay.day.condition.icon)

                cardWeather.setOnClickListener {
                    onInteractionListener.onShowDetail()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = CardWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        _context = parent.context
        return WeatherViewHolder(binding, onInteractionListener)
    }

    override fun getItemCount(): Int = forecastDay.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(forecastDay[position])
    }
}

