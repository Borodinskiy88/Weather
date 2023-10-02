package ru.borodinskiy.aleksei.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.borodinskiy.aleksei.weather.R
import ru.borodinskiy.aleksei.weather.adapter.DayAdapter
import ru.borodinskiy.aleksei.weather.databinding.FragmentDayBinding
import ru.borodinskiy.aleksei.weather.dto.Hour
import ru.borodinskiy.aleksei.weather.utils.ReformatValues.reformatDate
import ru.borodinskiy.aleksei.weather.viewmodel.WeatherViewModel

@AndroidEntryPoint
class DayFragment : Fragment() {

    companion object {
        const val NAME_ENG = "nameEng"
        const val NAME_RUS = "nameRus"
        const val BACKGROUND = "background"
    }


    private lateinit var recyclerView: RecyclerView
    private val viewModel: WeatherViewModel by activityViewModels()
    private lateinit var binding: FragmentDayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDayBinding.inflate(inflater, container, false)

        val nameEng = arguments?.getString(NAME_ENG).toString()
        val nameRus = arguments?.getString(NAME_RUS).toString()
        val background = arguments?.getInt(BACKGROUND)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.back.setOnClickListener {
            val bundle = bundleOf(
                Pair("dayEng", nameEng),
                Pair("dayRus", nameRus),
                Pair("dayBackground", background)
            )
            findNavController().navigate(R.id.action_dayFragment_to_weatherFragment, bundle)
        }

        viewModel.getWeather(nameEng).observe(viewLifecycleOwner) {
            setAdapterInRecycleView(it.forecast.forecastDay[0].hour)
            binding.city.text = nameRus
            binding.date.text = reformatDate(it.forecast.forecastDay[0].date)
            this.view?.background =
                background?.let { background ->
                    ContextCompat.getDrawable(
                        requireContext(),
                        background
                    )
                }

        }

        binding.apply {
            todayButton.setOnClickListener {
                viewModel.getWeather(nameEng).observe(viewLifecycleOwner) {
                    setAdapterInRecycleView(it.forecast.forecastDay[0].hour)
                    date.text = reformatDate(it.forecast.forecastDay[0].date)
                }
            }
            tomorrowButton.setOnClickListener {
                viewModel.getWeather(nameEng).observe(viewLifecycleOwner) {
                    setAdapterInRecycleView(it.forecast.forecastDay[1].hour)
                    date.text = reformatDate(it.forecast.forecastDay[1].date)
                }
            }
            afterTomorrowButton.setOnClickListener {
                viewModel.getWeather(nameEng).observe(viewLifecycleOwner) {
                    setAdapterInRecycleView(it.forecast.forecastDay[2].hour)
                    date.text = reformatDate(it.forecast.forecastDay[2].date)
                }
            }
        }

        return binding.root
    }


    private fun setAdapterInRecycleView(hour: List<Hour>) {

        binding.recyclerView.adapter = DayAdapter(hour)
    }
}