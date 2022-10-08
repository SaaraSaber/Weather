package com.example.weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.CITY_ID
import com.example.weather.adapter.FiveDayForecastAdapter
import com.example.weather.data.DailyForecastsModel
import com.example.weather.databinding.FragmentFiveDayForecastBinding
import com.example.weather.viewModel.FiveDayForecastViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FiveDayForecastFragment : Fragment() {
    private lateinit var binding: FragmentFiveDayForecastBinding
    private val viewModel: FiveDayForecastViewModel by viewModel()
    private lateinit var adapter: FiveDayForecastAdapter
    private var cityId: String? = null
    //private lateinit var lsDailyForecasts: ArrayList<DailyForecastsModel>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFiveDayForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        cityId = requireArguments().getString(CITY_ID, "0")

        viewModel.sendOnDayForecastApi(requireActivity(), cityId!!)
        viewModel.liveData.observe(viewLifecycleOwner) {
            if (it.DailyForecasts.isEmpty()) return@observe
            adapter = FiveDayForecastAdapter(requireActivity())
            adapter.lsDailyForecastsModel = it.DailyForecasts
            setRecFiveDayForecast()
        }
    }

    private fun setRecFiveDayForecast() {
        binding.recFiveDayForecast.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recFiveDayForecast.adapter = adapter
    }
}