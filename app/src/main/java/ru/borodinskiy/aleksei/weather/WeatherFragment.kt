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
import ru.borodinskiy.aleksei.weather.dto.Condition
import ru.borodinskiy.aleksei.weather.dto.Day
import ru.borodinskiy.aleksei.weather.dto.Forecast
import ru.borodinskiy.aleksei.weather.dto.ForecastDay
import ru.borodinskiy.aleksei.weather.dto.Location
import ru.borodinskiy.aleksei.weather.dto.Weather
import ru.borodinskiy.aleksei.weather.viewmodel.WeatherViewModel

@AndroidEntryPoint
class WeatherFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView

    //    private lateinit var adapter: WeatherAdapter
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

//        adapter = WeatherAdapter()

//        recyclerView.adapter = adapter

        val list = listOf(
            Weather(
                Location("T", "T"),
                Forecast(
                    forecastDay = listOf(
                        ForecastDay(
                            Day(
                                "24", "36", "45",
                                Condition("", "freeze")
                            ),
                            "06.09.2023"
                        ),
                        ForecastDay(
                            Day(
                                "24", "36", "45",
                                Condition("", "freeze")
                            ),
                            "07.09.2023"
                        ),
                        ForecastDay(
                            Day(
                                "24", "36", "45",
                                Condition("", "freeze")
                            ),
                            "08.09.2023"
                        ),
                        ForecastDay(
                            Day(
                                "24", "36", "45",
                                Condition("", "freeze")
                            ),
                            "09.09.2023"
                        ),
                        ForecastDay(
                            Day(
                                "24", "36", "45",
                                Condition("", "freeze")
                            ),
                            "10.09.2023"
                        ),
                    )
                )
            ),
//            Weather(
//                Location("T", "T"),
//                Forecast(
//                    forecastDay = listOf(
//                        ForecastDay(
//                            Day(
//                                "24", "36", "45",
//                                Condition("", "freeze")
//                            ),
//                            "07.09.2023"
//                        )
//                    )
//                )
//            ),
//
//            Weather(
//                Location("T", "T"),
//                Forecast(
//                    forecastDay = listOf(
//                        ForecastDay(
//                            Day(
//                                "24", "36", "45",
//                                Condition("", "freeze")
//                            ),
//                            "08.09.2023"
//                        )
//                    )
//                )
//            ),
//
//            Weather(
//                Location("T", "T"),
//                Forecast(
//                    forecastDay = listOf(
//                        ForecastDay(
//                            Day(
//                                "24", "36", "45",
//                                Condition("", "freeze")
//                            ),
//                            "09.09.2023"
//                        )
//                    )
//                )
//            ),
//
//            Weather(
//                Location("T", "T"),
//                Forecast(
//                    forecastDay = listOf(
//                        ForecastDay(
//                            Day(
//                                "24", "36", "45",
//                                Condition("", "freeze")
//                            ),
//                            "10.09.2023"
//                        )
//                    )
//                )
//            ),
        )
//        adapter.submitList(list)

//        viewModel.data.observe(viewLifecycleOwner) {
//            adapter.submitList(it)
//        }

        //TODO тестовые данные работают только если в адаптере заменить weather: Weather на forecastDay: ForecastDay

        binding.weatherButton.setOnClickListener {
            //           Поменять фон
            binding.headline.text = "Москва"
//            binding.headline.text = viewModel.data.value?.get(0)?.location?.city
            this.view?.background = ContextCompat.getDrawable(requireContext(), R.drawable.moscow)
//            viewModel.getWeatherMoscow().observe(viewLifecycleOwner) {
//
//                adapter.submitList(it)
//            }
            viewModel.getWeather.observe(viewLifecycleOwner) {
                setAdapterInRecycleView(it.forecast.forecastDay)
            }
        }


//        binding.refreshWeatherButton.setOnClickListener {
//            viewModel.loadWeather()
//        }
    }

    private fun setAdapterInRecycleView(forecastDay: List<ForecastDay>) {
        binding.recyclerView.adapter = WeatherAdapter(forecastDay)
    }

}