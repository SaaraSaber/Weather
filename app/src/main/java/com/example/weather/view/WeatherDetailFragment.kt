package com.example.weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.weather.CITY_ID
import com.example.weather.data.ModelGetCurrentCondition
import com.example.weather.databinding.FragmentWeatherDetailBinding
import com.example.weather.viewModel.WeatherDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class WeatherDetailFragment : Fragment() {
    private lateinit var binding: FragmentWeatherDetailBinding
    private val viewModel: WeatherDetailViewModel by viewModel()
    private var cityId: String? = null
    private lateinit var lsCurrentCondition: ArrayList<ModelGetCurrentCondition>

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
            findNavController().popBackStack()
        }
        cityId = requireArguments().getString(CITY_ID, "0")
        viewModel.sendCurrentConditionApi(requireActivity(), cityId!!)
        viewModel.liveData.observe(viewLifecycleOwner) {
            if (it.isEmpty()) return@observe
            lsCurrentCondition = ArrayList()
            lsCurrentCondition.addAll(it)
            setCurrentConditionData()
        }

    }

    private lateinit var currentTemperatureIcon: String

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

}