package ru.borodinskiy.aleksei.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.borodinskiy.aleksei.weather.R
import ru.borodinskiy.aleksei.weather.adapter.DayAdapter
import ru.borodinskiy.aleksei.weather.databinding.FragmentDayBinding
import ru.borodinskiy.aleksei.weather.dto.Hour
import ru.borodinskiy.aleksei.weather.utils.ReformatValues.reformatDate
import ru.borodinskiy.aleksei.weather.viewmodel.WeatherViewModel

class DayFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val viewModel: WeatherViewModel by activityViewModels()
    private lateinit var binding: FragmentDayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDayBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_dayFragment_to_weatherFragment)
        }

        viewModel.getWeather("Moscow").observe(viewLifecycleOwner) {
            setAdapterInRecycleView(it.forecast.forecastDay[0].hour)
            binding.city.text = "Москва"
            binding.date.text = reformatDate(it.forecast.forecastDay[0].date)
            this.view?.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.moscow)

        }

        binding.apply {
            todayButton.setOnClickListener {
                viewModel.getWeather("Moscow").observe(viewLifecycleOwner) {
                    setAdapterInRecycleView(it.forecast.forecastDay[0].hour)
                    city.text = "Москва"
//                    date.text = it.forecast.forecastDay[0].date
                    date.text = reformatDate(it.forecast.forecastDay[0].date)
                }
            }
            tomorrowButton.setOnClickListener {
                viewModel.getWeather("Moscow").observe(viewLifecycleOwner) {
                    setAdapterInRecycleView(it.forecast.forecastDay[1].hour)
                    date.text = reformatDate(it.forecast.forecastDay[1].date)
                }
            }
            afterTomorrowButton.setOnClickListener {
                viewModel.getWeather("Moscow").observe(viewLifecycleOwner) {
                    setAdapterInRecycleView(it.forecast.forecastDay[2].hour)
                    date.text = reformatDate(it.forecast.forecastDay[2].date)
                }
            }
        }

    }

    private fun setAdapterInRecycleView(hour: List<Hour>) {

        binding.recyclerView.adapter = DayAdapter(hour)
    }
}