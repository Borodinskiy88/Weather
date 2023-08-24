package ru.borodinskiy.aleksei.weather

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.borodinskiy.aleksei.weather.adapter.WeatherAdapter
import ru.borodinskiy.aleksei.weather.databinding.ActivityMainBinding
import ru.borodinskiy.aleksei.weather.viewmodel.WeatherViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WeatherAdapter
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(baseContext)

        adapter = WeatherAdapter()

        recyclerView.adapter = adapter

        binding.refreshWeatherButton.setOnClickListener {
            viewModel.getMoscow().observe(this) {
                adapter.submitList(it)
            }
        }
    }
}