package ru.borodinskiy.aleksei.weather

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
import ru.borodinskiy.aleksei.weather.viewmodel.WeatherViewModel

@AndroidEntryPoint
class WeatherFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WeatherAdapter
    private val viewModel: WeatherViewModel by activityViewModels()
    private lateinit var binding: FragmentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = WeatherAdapter()

        recyclerView.adapter = adapter

//        val list = listOf(
//            Weather(
//                "Тула", "Россия", "26.08.2023", "//cdn.weatherapi.com/weather/64x64/day/116.png",
//                "Облачно", "+17", "28 км/ч", "65%"
//            ),
//            Weather(
//                "Тула", "Россия", "27.08.2023", "//cdn.weatherapi.com/weather/64x64/day/113.png",
//                "Дождь", "+27", "48 км/ч", "65%"
//            ),
//        )
//        adapter.submitList(list)

//        viewModel.data.observe(viewLifecycleOwner) {
//            adapter.submitList(it)
//        }

        binding.weatherButton.setOnClickListener {
            //           Поменять фон
            binding.headline.text = "Москва"
//            binding.headline.text = viewModel.data.value?.get(0)?.location?.city
            this.view?.background = ContextCompat.getDrawable(requireContext(), R.drawable.moscow)
            viewModel.getWeatherMoscow().observe(viewLifecycleOwner) {

                adapter.submitList(it)
            }
        }


//        binding.refreshWeatherButton.setOnClickListener {
//            viewModel.loadWeather()
//        }
    }

}