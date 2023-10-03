package ru.borodinskiy.aleksei.weather.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.borodinskiy.aleksei.weather.databinding.CardDayBinding
import ru.borodinskiy.aleksei.weather.dto.Hour
import ru.borodinskiy.aleksei.weather.util.load
import ru.borodinskiy.aleksei.weather.utils.ReformatValues.reformatTime

class DayAdapter(private val hour: List<Hour>) :
    RecyclerView.Adapter<DayAdapter.DayViewHolder>() {
    private lateinit var _context: Context

    inner class DayViewHolder(private val binding: CardDayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        fun bind(hour: Hour) {
            binding.apply {

                time.text = reformatTime(hour.time)
                iconWeather.load(hour.condition.icon)

                val currentTemp = hour.tempHour.toInt()
                temp.text = if (currentTemp > 0) {
                    "+$currentTemp °C"
                } else "$currentTemp °C"

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val binding = CardDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        _context = parent.context
        return DayViewHolder(binding)
    }

    override fun getItemCount(): Int = hour.size

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.bind(hour[position])
    }
}