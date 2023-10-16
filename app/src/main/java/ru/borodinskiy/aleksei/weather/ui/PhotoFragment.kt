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
import dagger.hilt.android.AndroidEntryPoint
import ru.borodinskiy.aleksei.weather.R
import ru.borodinskiy.aleksei.weather.databinding.FragmentPhotoBinding
import ru.borodinskiy.aleksei.weather.viewmodel.WeatherViewModel

@AndroidEntryPoint
class PhotoFragment : Fragment() {
    companion object {
        const val NAME_ENG = "nameEng"
        const val NAME_RUS = "nameRus"
        const val BACKGROUND = "background"
    }

    private lateinit var binding: FragmentPhotoBinding
    private val viewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoBinding.inflate(inflater, container, false)

        val nameEng = arguments?.getString(NAME_ENG).toString()
        val nameRus = arguments?.getString(NAME_RUS).toString()
        val background = arguments?.getInt(BACKGROUND)

        viewModel.getWeather(nameEng).observe(viewLifecycleOwner) {
            binding.city.text = nameRus

            this.view?.background =
                background?.let { background ->
                    ContextCompat.getDrawable(
                        requireContext(),
                        background
                    )
                }

        }

        binding.back.setOnClickListener {
            val bundle = bundleOf(
                Pair("dayEng", nameEng),
                Pair("dayRus", nameRus),
                Pair("dayBackground", background)
            )
            findNavController().navigate(R.id.action_photoFragment_to_weatherFragment, bundle)
        }


        return binding.root
    }

}