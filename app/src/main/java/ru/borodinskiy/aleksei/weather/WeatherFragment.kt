package ru.borodinskiy.aleksei.weather

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.borodinskiy.aleksei.weather.adapter.WeatherAdapter
import ru.borodinskiy.aleksei.weather.databinding.FragmentWeatherBinding
import ru.borodinskiy.aleksei.weather.dto.ForecastDay
import ru.borodinskiy.aleksei.weather.util.load
import ru.borodinskiy.aleksei.weather.viewmodel.WeatherViewModel

@AndroidEntryPoint
class WeatherFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val viewModel: WeatherViewModel by activityViewModels()
    private lateinit var binding: FragmentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        binding.weatherButton.setOnClickListener {
            //           Поменять фон
            binding.headline.text = "Москва"
//            binding.headline.text = viewModel.data.value?.get(0)?.location?.city
            this.view?.background = ContextCompat.getDrawable(requireContext(), R.drawable.moscow)

            viewModel.getWeather.observe(viewLifecycleOwner) {
                setAdapterInRecycleView(it.forecast.forecastDay)
                val temp = it.forecast.forecastDay[0].day.temperature.toInt()
                binding.headTemp.text = if (temp > 0) {
                    "+$temp °C"
                } else "$temp °C"
                //          binding.headTemp.text = it.forecast.forecastDay[0].day.temperature.toInt().toString() + " °C"
                binding.headIcon.load(it.forecast.forecastDay[0].day.condition.icon)
            }
        }
    }

    private fun setAdapterInRecycleView(forecastDay: List<ForecastDay>) {
        binding.recyclerView.adapter = WeatherAdapter(forecastDay)
    }

}