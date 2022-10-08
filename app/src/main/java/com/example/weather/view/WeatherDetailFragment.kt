package com.example.weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.CITY_ID
import com.example.weather.R
import com.example.weather.adapter.TwelveHoursForecastAdapter
import com.example.weather.data.GetCurrentConditionModel
import com.example.weather.databinding.FragmentWeatherDetailBinding
import com.example.weather.viewModel.CityFinderViewModel
import com.example.weather.viewModel.OnDayForecastViewModel
import com.example.weather.viewModel.TwelveHoursForecastViewModel
import com.example.weather.viewModel.WeatherDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class WeatherDetailFragment : Fragment() {
    private lateinit var binding: FragmentWeatherDetailBinding
    private val viewModelWeatherDetail: WeatherDetailViewModel by viewModel()
    private val viewModelOnDayForecast: OnDayForecastViewModel by viewModel()
    private val viewModelTwelveHoursForecast: TwelveHoursForecastViewModel by viewModel()
    private val viewModelCityFinder: CityFinderViewModel by viewModel()
    private var cityId: String? = null
    private lateinit var lsCurrentCondition: ArrayList<GetCurrentConditionModel>
    private lateinit var currentTemperatureIcon: String
    private lateinit var adapter: TwelveHoursForecastAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backBtn.setOnClickListener {
            viewModelCityFinder.cleanCityIdFromSharedPreference()
            findNavController().navigate(R.id.action_weatherDetailFragment_to_cityFinderFragment)
        }

        binding.btnChangeFragmentFiveDayForecast.setOnClickListener {
            view.let {
                val bundle=Bundle()
                bundle.putString(CITY_ID,cityId)
               findNavController()
                    .navigate(R.id.action_weatherDetailFragment_to_fiveDayForecastFragment,bundle)
            }
        }
        cityId = requireArguments().getString(CITY_ID, "0")
        viewModelWeatherDetail.sendCurrentConditionApi(requireActivity(), cityId!!)
        viewModelOnDayForecast.sendOnDayForecastApi(requireActivity(), cityId!!)
        viewModelTwelveHoursForecast.sendTwelveHoursForecastApi(requireActivity(), cityId!!)

        observerWeatherDetailApi()
        observerOnDayForecastApi()
        observerTwelveHoursForecastApi()
    }

    private fun observerWeatherDetailApi() {
        viewModelWeatherDetail.liveData.observe(viewLifecycleOwner) {
            if (it.isEmpty()) return@observe
            lsCurrentCondition = ArrayList()
            lsCurrentCondition.addAll(it)

            setCurrentConditionData()
        }
    }

    private fun setCurrentConditionData() {
        val tempModelGetCurrentCondition = lsCurrentCondition[0]
        val convertTemperatureToInt: Int =
            tempModelGetCurrentCondition.Temperature.Metric.Value.toInt()

        binding.tvCurrentTimeTemperature.text = convertTemperatureToInt.toString()
        currentTemperatureIcon = tempModelGetCurrentCondition.WeatherIcon.toString()
        binding.imgCurrentTemperatureIcon.setBackgroundResource(
            resources.getIdentifier(
                "s_$currentTemperatureIcon",
                "drawable", requireActivity().packageName
            )
        )
        binding.tvCurrentDayConditionName.text = tempModelGetCurrentCondition.WeatherText
        binding.tvCurrentWindSpeed.text =
            tempModelGetCurrentCondition.Wind.Speed.Metric.Value.toString()
        binding.tvCurrentWindDirection.text = tempModelGetCurrentCondition.Wind.Direction.Localized
        binding.imgWindDirection.rotation =
            tempModelGetCurrentCondition.Wind.Direction.Degrees.toFloat()
        getCurrentData()
    }

    private fun getCurrentData() {
        val todayData = Calendar.getInstance()
        val currentYear = todayData.get(Calendar.YEAR)
        val currentMonth = todayData.get(Calendar.MONTH)
        val currentDay = todayData.get(Calendar.DAY_OF_MONTH)

        val sdf = SimpleDateFormat("hh:mm", Locale.ENGLISH)
        val currentCompleteData = sdf.format(Date())
        val indexOfSpace = currentCompleteData.indexOf("", 0)

        binding.tvCurrentTime.text =
            currentCompleteData.substring(indexOfSpace, currentCompleteData.length)
        binding.tvCurrentDate.text = "$currentYear/$currentMonth/$currentDay"
    }

    private fun observerOnDayForecastApi() {
        viewModelOnDayForecast.liveData.observe(viewLifecycleOwner) {
            binding.tvMinMaxTemperature.text =
                "${it.DailyForecasts[0].Temperature.Minimum.Value}˚/ ${it.DailyForecasts[0].Temperature.Maximum.Value}˚"
            binding.tvRainPercent.text =
                it.DailyForecasts[0].Day.precipitationProbability.toString()
        }

    }

    private fun observerTwelveHoursForecastApi() {
        viewModelTwelveHoursForecast.liveData.observe(viewLifecycleOwner) {
            adapter = TwelveHoursForecastAdapter(requireActivity())
            adapter.lsTwelveHoursForecastViewModel = it
            initRecTwelveHoursForecast()

        }
    }

    private fun initRecTwelveHoursForecast() {
        binding.recTwelveHoursForecast.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.recTwelveHoursForecast.adapter = adapter
    }

}