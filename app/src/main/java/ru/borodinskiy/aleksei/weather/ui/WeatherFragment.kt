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

        val dayEng = arguments?.getString(DAY_ENG) ?: "Moscow"
        val dayRus = arguments?.getString(DAY_RUS) ?: "Москва"
        val dayBackground = arguments?.getInt(DAY_BACKGROUND) ?: R.drawable.moscow

        callCity(dayEng, dayRus, dayBackground)

        binding.showPhoto.setOnClickListener {

            val bundle = bundleOf(
                Pair("nameEng", dayEng),
                Pair("nameRus", dayRus),
                Pair("background", dayBackground)
            )
            findNavController().navigate(R.id.action_weatherFragment_to_photoFragment, bundle)
        }

        binding.detailButton.setOnClickListener {

            val bundle = bundleOf(
                Pair("nameEng", dayEng),
                Pair("nameRus", dayRus),
                Pair("background", dayBackground)
            )
            findNavController().navigate(R.id.action_weatherFragment_to_dayFragment, bundle)

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

                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
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

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.anadyr -> {
                            val nameEng = "Anadyr"
                            val nameRus = "Анадырь"
                            val background = R.drawable.anadyr

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.arkhangelsk -> {
                            val nameEng = "Arkhangelsk"
                            val nameRus = "Архангельск"
                            val background = R.drawable.arkhangelsk

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.blagoveshchensk -> {
                            val nameEng = "Blagoveshchensk"
                            val nameRus = "Благовещенск"
                            val background = R.drawable.blagoveshensk

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.bryansk -> {
                            val nameEng = "Bryansk"
                            val nameRus = "Брянск"
                            val background = R.drawable.bryansk

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
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

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.vladivostok -> {
                            val nameEng = "Vladivostok"
                            val nameRus = "Владивосток"
                            val background = R.drawable.vladivostok

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
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

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.volgograd -> {
                            val nameEng = "Volgograd"
                            val nameRus = "Волгоград"
                            val background = R.drawable.volgograd

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.volokolamsk -> {
                            val nameEng = "Volokolamsk"
                            val nameRus = "Волоколамск"
                            val background = R.drawable.volokolamsk

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.voronezh -> {
                            val nameEng = "Voronezh"
                            val nameRus = "Воронеж"
                            val background = R.drawable.voronezh

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.vyborg -> {
                            val nameEng = "Vyborg"
                            val nameRus = "Выборг"
                            val background = R.drawable.vyborg

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.dmitrov -> {
                            val nameEng = "Dmitrov"
                            val nameRus = "Дмитров"
                            val background = R.drawable.dmitrov

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.ekaterinburg -> {
                            val nameEng = "Ekaterinburg"
                            val nameRus = "Екатеринбург"
                            val background = R.drawable.ekaterinburg

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.zvenigorod -> {
                            val nameEng = "Zvenigorod"
                            val nameRus = "Звенигород"
                            val background = R.drawable.zvenigorod

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.irkutsk -> {
                            val nameEng = "Irkutsk"
                            val nameRus = "Иркутск"
                            val background = R.drawable.irkutsk

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.yoshkar_ola -> {
                            val nameEng = "Yoshkar-Ola"
                            val nameRus = "Йошкар-Ола"
                            val background = R.drawable.yoshkar_ola

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.kaluga -> {
                            val nameEng = "Kaluga"
                            val nameRus = "Калуга"
                            val background = R.drawable.kaluga

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
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

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
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

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.klin -> {
                            val nameEng = "Klin"
                            val nameRus = "Клин"
                            val background = R.drawable.klin

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.kolomna -> {
                            val nameEng = "Kolomna"
                            val nameRus = "Коломна"
                            val background = R.drawable.kolomna

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.krasnodar -> {
                            val nameEng = "Krasnodar"
                            val nameRus = "Краснодар"
                            val background = R.drawable.krasnodar

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.krasnoyarsk -> {
                            val nameEng = "Krasnoyarsk"
                            val nameRus = "Красноярск"
                            val background = R.drawable.krasnoyarsk

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.lipetsk -> {
                            val nameEng = "Lipetsk"
                            val nameRus = "Липецк"
                            val background = R.drawable.lipeck

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.magadan -> {
                            val nameEng = "Magadan"
                            val nameRus = "Магадан"
                            val background = R.drawable.magadan

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.murmansk -> {
                            val nameEng = "Murmansk"
                            val nameRus = "Мурманск"
                            val background = R.drawable.murmansk

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
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

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.novorossiysk -> {
                            val nameEng = "Novorossiysk"
                            val nameRus = "Новороссийск"
                            val background = R.drawable.novorossiysk

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.ozery -> {
                            val nameEng = "Ozery"
                            val nameRus = "Озёры"
                            val background = R.drawable.ozery

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.orel -> {
                            val nameEng = "Orel"
                            val nameRus = "Орёл"
                            val background = R.drawable.orel

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
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

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
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

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.perm -> {
                            val nameEng = "Perm"
                            val nameRus = "Пермь"
                            val background = R.drawable.perm

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.petropavlovsk_kamchatsky -> {
                            val nameEng = "Petropavlovsk-Kamchatsky"
                            val nameRus = "Петропавловск-К."
                            val background = R.drawable.petropavlovsk_k

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.petrozavodsk -> {
                            val nameEng = "Petrozavodsk"
                            val nameRus = "Петрозаводск"
                            val background = R.drawable.petrozavodsk

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.pskov -> {
                            val nameEng = "Pskov"
                            val nameRus = "Псков"
                            val background = R.drawable.pskov

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.pyatigorsk -> {
                            val nameEng = "Pyatigorsk"
                            val nameRus = "Пятигорск"
                            val background = R.drawable.pyatigorsk

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.rzhev -> {
                            val nameEng = "Rzhev"
                            val nameRus = "Ржев"
                            val background = R.drawable.rzhev

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.rostov_on_don -> {
                            val nameEng = "Rostov-on-Don"
                            val nameRus = "Ростов-на-Дону"
                            val background = R.drawable.rostov_na_donu

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.ryazan -> {
                            val nameEng = "Ryazan"
                            val nameRus = "Рязань"
                            val background = R.drawable.ryazan

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
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

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.saransk -> {
                            val nameEng = "Saransk"
                            val nameRus = "Саранск"
                            val background = R.drawable.saransk

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.saratov -> {
                            val nameEng = "Saratov"
                            val nameRus = "Саратов"
                            val background = R.drawable.saratov

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
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

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.smolensk -> {
                            val nameEng = "Smolensk"
                            val nameRus = "Смоленск"
                            val background = R.drawable.smolensk

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.sochi -> {
                            val nameEng = "Sochi"
                            val nameRus = "Сочи"
                            val background = R.drawable.sochi

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.stavropol -> {
                            val nameEng = "Stavropol"
                            val nameRus = "Ставрополь"
                            val background = R.drawable.stavropol

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
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

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.syzran -> {
                            val nameEng = "Syzran"
                            val nameRus = "Сызрань"
                            val background = R.drawable.syzran

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.tver -> {
                            val nameEng = "Tver"
                            val nameRus = "Тверь"
                            val background = R.drawable.tver

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.tobolsk -> {
                            val nameEng = "Tobolsk"
                            val nameRus = "Тобольск"
                            val background = R.drawable.tobolsk

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
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

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.ulan_ude -> {
                            val nameEng = "Ulan-Ude"
                            val nameRus = "Улан-Удэ"
                            val background = R.drawable.ulan_ude

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.ufa -> {
                            val nameEng = "Ufa"
                            val nameRus = "Уфа"
                            val background = R.drawable.ufa

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
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

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.chelyabinsk -> {
                            val nameEng = "Chelyabinsk"
                            val nameRus = "Челябинск"
                            val background = R.drawable.chelyabinsk

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
                            }

                            true
                        }

                        R.id.elista -> {
                            val nameEng = "Elista"
                            val nameRus = "Элиста"
                            val background = R.drawable.elista

                            callCity(nameEng, nameRus, background)
                            recyclerView.isVisible = true

                            binding.detailButton.setOnClickListener {
                                setBundle(nameEng, nameRus, background)
                            }

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
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

                            binding.showPhoto.setOnClickListener {
                                setBundleToPhoto(nameEng, nameRus, background)
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

    private fun setBundleToPhoto(nameEng: String, nameRus: String, background: Int) {

        val bundle = bundleOf(
            Pair("nameEng", nameEng),
            Pair("nameRus", nameRus),
            Pair("background", background)
        )
        findNavController().navigate(R.id.action_weatherFragment_to_photoFragment, bundle)
    }

}