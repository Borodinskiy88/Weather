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


//                val formatter = DateTimeFormatter.ofPattern("dd-MM")
//                val date = LocalDate.parse(forecastDay.date)
//                val new = date.format(formatter)

//                val timeString = hour.time
//                val timeObj = SimpleDateFormat("yyyy-MM-dd HH:mm").parse(timeString)
//                val currentTime =
//                    timeObj?.let { SimpleDateFormat("HH:mm", Locale("ru")).format(it) }

//                time.text = currentTime
                time.text = reformatTime(hour.time)
                iconWeather.load(hour.condition.icon)
//                temp.text = hour.tempHour.toInt().toString() + " °C"

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