package ru.borodinskiy.aleksei.weather.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.borodinskiy.aleksei.weather.R
import ru.borodinskiy.aleksei.weather.adapter.OnInteractionListener
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

        //TODO Если бандл не пустой, открывай город из Бандла, если пустой, то Москву

        callCity("Moscow", "Москва", R.drawable.moscow)
        binding.weatherButton.isVisible = true

//        binding.detailButton.setOnClickListener {
//            findNavController().navigate(R.id.action_weatherFragment_to_dayFragment)
//        }

        binding.weatherButton.setOnClickListener {
            recyclerView.isVisible = false
            PopupMenu(it.context, it).apply {
                inflate(R.menu.city_menu)
                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.moscow -> {
                            callCity("Moscow", "Москва", R.drawable.moscow)
                            //Прячем погоду
                            recyclerView.isVisible = true

                            recyclerView.setOnClickListener {
                                val bundle = bundleOf(
                                    //   Pair("name")
                                )
                                findNavController().navigate(R.id.action_weatherFragment_to_dayFragment)
                            }
                            true
                        }

                        R.id.st_petersburg -> {
                            callCity("Saint Petersburg", "Санкт-Петербург", R.drawable.piter)
                            recyclerView.isVisible = true
                            true
                        }

                        R.id.v_novgorod -> {
                            callCity("Veliky Novgorod", "В.Новгород", R.drawable.velikiy_novgorod)
                            recyclerView.isVisible = true
                            true
                        }

                        R.id.vladimir -> {
                            callCity("Vladimir", "Владимир", R.drawable.vladimir)
                            recyclerView.isVisible = true
                            true
                        }

                        R.id.kazan -> {
                            callCity("Kazan", "Казань", R.drawable.kazan)
                            recyclerView.isVisible = true
                            true
                        }

                        R.id.kaliningrad -> {
                            callCity("Kaliningrad", "Калининград", R.drawable.kaliningrad)
                            recyclerView.isVisible = true
                            true
                        }

                        R.id.n_novgorod -> {
                            callCity("Nizhny Novgorod", "Н.Новгород", R.drawable.nizny_novgorod)
                            recyclerView.isVisible = true
                            true
                        }

                        R.id.penza -> {
                            callCity("Penza", "Пенза", R.drawable.penza)
                            recyclerView.isVisible = true
                            true
                        }

                        R.id.pereslavl_z -> {
                            callCity("Pereslavl-Zalessky", "Переславль-З.", R.drawable.pereslavl)
                            recyclerView.isVisible = true
                            true
                        }

                        R.id.samara -> {
                            callCity("Samara", "Самара", R.drawable.samara)
                            recyclerView.isVisible = true
                            true
                        }

                        R.id.s_posad -> {
                            callCity("Sergiyev Posad", "Сергиев Посад", R.drawable.sergiev_posad)
                            recyclerView.isVisible = true
                            true
                        }

                        R.id.suzdal -> {
                            callCity("Suzdal", "Суздаль", R.drawable.suzdal)
                            recyclerView.isVisible = true
                            true
                        }

                        R.id.tula -> {
                            callCity("Tula", "Тула", R.drawable.tula)
                            recyclerView.isVisible = true
                            true
                        }

                        R.id.cheboksary -> {
                            callCity("Cheboksary", "Чебоксары", R.drawable.cheboksary)
                            recyclerView.isVisible = true
                            true
                        }

                        R.id.yaroslavl -> {
                            callCity("Yaroslavl", "Ярославль", R.drawable.yaroslavl)
                            recyclerView.isVisible = true
                            true
                        }

                        else -> false
                    }
                }
            }.show()
        }

    }

    private fun callCity(namEng: String, nameRus: String, image: Int) {
        //           Поменять фон
        binding.headline.text = nameRus
        this.view?.background =
            ContextCompat.getDrawable(requireContext(), image)

        observeCity(namEng)
    }

    private fun setAdapterInRecycleView(forecastDay: List<ForecastDay>) {
        binding.recyclerView.adapter = WeatherAdapter(forecastDay, object : OnInteractionListener {
            override fun onShowDetail() {
                val bundle = bundleOf(
                    // Pair("", forecastDay[0].day.)
                )
                findNavController().navigate(R.id.action_weatherFragment_to_dayFragment)
            }

        })
    }

    private fun observeCity(cityName: String) {
        viewModel.getWeather(cityName).observe(viewLifecycleOwner) {

            setAdapterInRecycleView(it.forecast.forecastDay)

            val currentTemp = it.current.tempCurrent.toInt()
            binding.headTemp.text = if (currentTemp > 0) {
                "+$currentTemp °C"
            } else "$currentTemp °C"

            binding.headIcon.load(it.current.condition.icon)
//            val temp = it.forecast.forecastDay[0].day.temperatureMax.toInt()
//            binding.headTemp.text = if (temp > 0) {
//                "+$temp °C"
//            } else "$temp °C"
//            binding.headIcon.load(it.forecast.forecastDay[0].day.condition.icon)
        }
    }

}