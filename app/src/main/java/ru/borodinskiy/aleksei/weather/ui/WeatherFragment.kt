package ru.borodinskiy.aleksei.weather.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.borodinskiy.aleksei.weather.R
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

        callCity("Moscow", "Москва", R.drawable.moscow)
        binding.weatherButton.isVisible = true

        binding.weatherButton.setOnClickListener {
            callCity("Saint Petersburg", "Санкт-Петербург", R.drawable.piter)
        }

    }

    private fun callCity(namEng: String, nameRus: String, image: Int) {
        //           Поменять фон
        binding.headline.text = nameRus
        this.view?.background =
            ContextCompat.getDrawable(requireContext(), image)

        observeCity(namEng)
    }

//    private fun moscow() {
//            //           Поменять фон
//            binding.headline.text = "Москва"
//            this.view?.background =
//                ContextCompat.getDrawable(requireContext(), R.drawable.moscow)
//
//        observeCity("Moscow")
//    }
//
//    private fun stPetersburg() {
//        //           Поменять фон
//        binding.headline.text = "Санкт-Петербург"
//        this.view?.background =
//            ContextCompat.getDrawable(requireContext(), R.drawable.piter)
//
//        observeCity("Saint Petersburg")
//    }

    private fun setAdapterInRecycleView(forecastDay: List<ForecastDay>) {
        binding.recyclerView.adapter = WeatherAdapter(forecastDay)
    }

    private fun observeCity(cityName: String) {
        viewModel.getWeather(cityName).observe(viewLifecycleOwner) {
            setAdapterInRecycleView(it.forecast.forecastDay)
            val temp = it.forecast.forecastDay[0].day.temperatureMax.toInt()
            binding.headTemp.text = if (temp > 0) {
                "+$temp °C"
            } else "$temp °C"
            binding.headIcon.load(it.forecast.forecastDay[0].day.condition.icon)
        }
    }

}