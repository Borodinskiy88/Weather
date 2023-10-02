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
import ru.borodinskiy.aleksei.weather.adapter.WeatherAdapter
import ru.borodinskiy.aleksei.weather.databinding.FragmentWeatherBinding
import ru.borodinskiy.aleksei.weather.dto.ForecastDay
import ru.borodinskiy.aleksei.weather.util.load
import ru.borodinskiy.aleksei.weather.viewmodel.WeatherViewModel

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    companion object {
        const val DAY_ENG = "dayEng"
        const val DAY_RUS = "dayRus"
        const val DAY_BACKGROUND = "dayBackground"
    }

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
        val dayEng = arguments?.getString(DAY_ENG) ?: "Moscow"
        val dayRus = arguments?.getString(DAY_RUS) ?: "Москва"
        val dayBackground = arguments?.getInt(DAY_BACKGROUND) ?: R.drawable.moscow

        callCity(dayEng, dayRus, dayBackground)
//        val dayEng = arguments?.getString(DAY_ENG).toString()
//        val dayRus = arguments?.getString(DAY_RUS).toString()
//        val dayBackground = arguments?.getInt(DAY_BACKGROUND)
//
//        if (dayEng.isNotEmpty() && dayRus.isNotEmpty() && dayBackground != null) {
//            callCity(dayEng, dayRus, dayBackground)
//            binding.weatherButton.isVisible = true
//        } else {
//            callCity("Moscow", "Москва", R.drawable.moscow)
//            binding.weatherButton.isVisible = true
//            binding.detailButton.setOnClickListener {
//                val bundle = bundleOf(
//                    Pair("nameEng", "Moscow"),
//                    Pair("nameRus", "Москва"),
//                    Pair("background", R.drawable.moscow)
//                )
//                findNavController().navigate(R.id.action_weatherFragment_to_dayFragment, bundle)
//            }
//        }

        binding.detailButton.setOnClickListener {
            //           if (dayEng.isNotEmpty() && dayRus.isNotEmpty() && dayBackground != null) {
            val bundle = bundleOf(
                Pair("nameEng", dayEng),
                Pair("nameRus", dayRus),
                Pair("background", dayBackground)
            )
            findNavController().navigate(R.id.action_weatherFragment_to_dayFragment, bundle)
//            }
        }

        binding.weatherButton.setOnClickListener {
            recyclerView.isVisible = false
            PopupMenu(it.context, it).apply {
                inflate(R.menu.city_menu)
                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.moscow -> {
                            val nameEng = "Moscow"
                            val nameRus = "Москва"
                            val background = R.drawable.moscow

                            callCity(nameEng, nameRus, background)
                            //Прячем погоду
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.st_petersburg -> {
                            val nameEng = "Saint Petersburg"
                            val nameRus = "Санкт-Петербург"
                            val background = R.drawable.piter

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.v_novgorod -> {
                            val nameEng = "Veliky Novgorod"
                            val nameRus = "В.Новгород"
                            val background = R.drawable.velikiy_novgorod

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.vladimir -> {
                            val nameEng = "Vladimir"
                            val nameRus = "Владимир"
                            val background = R.drawable.vladimir

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.kazan -> {
                            val nameEng = "Kazan"
                            val nameRus = "Казань"
                            val background = R.drawable.kazan

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.kaliningrad -> {
                            val nameEng = "Kaliningrad"
                            val nameRus = "Калининград"
                            val background = R.drawable.kaliningrad

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.n_novgorod -> {
                            val nameEng = "Nizhny Novgorod"
                            val nameRus = "Н.Новгород"
                            val background = R.drawable.nizny_novgorod

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.penza -> {
                            val nameEng = "Penza"
                            val nameRus = "Пенза"
                            val background = R.drawable.penza

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.pereslavl_z -> {
                            val nameEng = "Pereslavl-Zalessky"
                            val nameRus = "Переславль-З."
                            val background = R.drawable.pereslavl

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.samara -> {
                            val nameEng = "Samara"
                            val nameRus = "Самара"
                            val background = R.drawable.samara

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.s_posad -> {
                            val nameEng = "Sergiyev Posad"
                            val nameRus = "Сергиев Посад"
                            val background = R.drawable.sergiev_posad

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.suzdal -> {
                            val nameEng = "Suzdal"
                            val nameRus = "Суздаль"
                            val background = R.drawable.suzdal

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.tula -> {
                            val nameEng = "Tula"
                            val nameRus = "Тула"
                            val background = R.drawable.tula

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.cheboksary -> {
                            val nameEng = "Cheboksary"
                            val nameRus = "Чебоксары"
                            val background = R.drawable.cheboksary

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.yaroslavl -> {
                            val nameEng = "Yaroslavl"
                            val nameRus = "Ярославль"
                            val background = R.drawable.yaroslavl

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

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
        binding.recyclerView.adapter = WeatherAdapter(forecastDay)
    }

    private fun observeCity(cityName: String) {
        viewModel.getWeather(cityName).observe(viewLifecycleOwner) {

            setAdapterInRecycleView(it.forecast.forecastDay)

            val currentTemp = it.current.tempCurrent.toInt()
            binding.headTemp.text = if (currentTemp > 0) {
                "+$currentTemp °C"
            } else "$currentTemp °C"

            binding.headIcon.load(it.current.condition.icon)

        }
    }

    private fun setBundle(nameEng: String, nameRus: String, background: Int) {

        val bundle = bundleOf(
            Pair("nameEng", nameEng),
            Pair("nameRus", nameRus),
            Pair("background", background)
        )
        findNavController().navigate(R.id.action_weatherFragment_to_dayFragment, bundle)
    }

}