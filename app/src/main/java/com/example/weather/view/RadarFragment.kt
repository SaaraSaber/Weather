package com.example.weather.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.weather.API_RADAR
import com.example.weather.R
import com.example.weather.common.ShowLoading
import com.example.weather.databinding.FragmentRadarBinding

class RadarFragment : Fragment() {
    private lateinit var binding: FragmentRadarBinding

    private lateinit var loading: ShowLoading

    private val radarWebViewTempAddress =
        "https://goweatherradar.com/en/widget/${API_RADAR}?lat=35.677&lng=51.3445&metricRain=mm&metricTemp=c&overlay=temp&zoom=5"

    private val radarWebViewRainAddress =
        "https://goweatherradar.com/en/widget/${API_RADAR}?lat=35.677&lng=51.3445&metricRain=mm&metricTemp=c&overlay=rain&zoom=5"

    private val radarWebViewSnowAddress =
        "https://goweatherradar.com/en/widget/${API_RADAR}?lat=35.677&lng=51.3445&metricRain=mm&metricTemp=c&overlay=snow&zoom=5"

    private val radarWebViewWindAddress =
        "https://goweatherradar.com/en/widget/${API_RADAR}?lat=35.677&lng=51.3445&metricRain=mm&metricTemp=c&overlay=wind&zoom=5"

    private val radarWebViewPressureAddress =
        "https://goweatherradar.com/en/widget/${API_RADAR}?lat=35.677&lng=51.3445&metricRain=mm&metricTemp=c&overlay=pressure&zoom=5"

    private val radarWebViewHumidityAddress =
        "https://goweatherradar.com/en/widget/${API_RADAR}?lat=35.677&lng=51.3445&metricRain=mm&metricTemp=c&overlay=humidity&zoom=5"

    private val radarWebViewCloudsAddress =
        "https://goweatherradar.com/en/widget/${API_RADAR}?lat=35.677&lng=51.3445&metricRain=mm&metricTemp=c&overlay=clouds&zoom=5"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRadarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setWebViewAddress()
        binding.webViewRadar.loadUrl(radarWebViewTempAddress)
        setTempOverlay()
        setRainOverlay()
        setSnowOverlay()
        setWindOverlay()
        setPressureOverlay()
        setHumidityOverlay()
        setCloudsOverlay()

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebViewAddress() {
        binding.webViewRadar.settings.javaScriptEnabled = true

        loading = ShowLoading(requireActivity())

        binding.webViewRadar.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                loading.dismissDialog()
            }

            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url!!)
                return true
            }
        }
    }

    private fun setTempOverlay() {
        binding.btnRadarTemp.setOnClickListener {
            changeSelectedButtonBackgroundToSelectedAndOtherButtonsToUnselected(binding.btnRadarTemp)
            setWebViewAddress()
            binding.webViewRadar.loadUrl(radarWebViewTempAddress)
        }
    }

    private fun setRainOverlay(){
        binding.btnRadarRain.setOnClickListener {
            changeSelectedButtonBackgroundToSelectedAndOtherButtonsToUnselected(binding.btnRadarRain)
            setWebViewAddress()
            binding.webViewRadar.loadUrl(radarWebViewRainAddress)
        }
    }

    private fun setSnowOverlay(){
        binding.btnRadarSnow.setOnClickListener {
            changeSelectedButtonBackgroundToSelectedAndOtherButtonsToUnselected(binding.btnRadarSnow)
            setWebViewAddress()
            binding.webViewRadar.loadUrl(radarWebViewSnowAddress)
        }
    }

    private fun setWindOverlay(){
        binding.btnRadarWind.setOnClickListener {
            changeSelectedButtonBackgroundToSelectedAndOtherButtonsToUnselected(binding.btnRadarWind)
            setWebViewAddress()
            binding.webViewRadar.loadUrl(radarWebViewWindAddress)
        }
    }

    private fun setPressureOverlay(){
        binding.btnRadarPressure.setOnClickListener {
            changeSelectedButtonBackgroundToSelectedAndOtherButtonsToUnselected(binding.btnRadarPressure)
            setWebViewAddress()
            binding.webViewRadar.loadUrl(radarWebViewPressureAddress)
        }
    }

    private fun setHumidityOverlay(){
        binding.btnRadarHumidity.setOnClickListener {
            changeSelectedButtonBackgroundToSelectedAndOtherButtonsToUnselected(binding.btnRadarHumidity)
            setWebViewAddress()
            binding.webViewRadar.loadUrl(radarWebViewHumidityAddress)
        }
    }

    private fun setCloudsOverlay(){
        binding.btnRadarClouds.setOnClickListener {
            changeSelectedButtonBackgroundToSelectedAndOtherButtonsToUnselected(binding.btnRadarClouds)
            setWebViewAddress()
            binding.webViewRadar.loadUrl(radarWebViewCloudsAddress)
        }
    }

    private fun changeSelectedButtonBackgroundToSelectedAndOtherButtonsToUnselected(selectedButton: Button) {
        binding.btnRadarTemp.setBackgroundResource(R.drawable.bg_btn_radar_layer_type_unselected)
        binding.btnRadarRain.setBackgroundResource(R.drawable.bg_btn_radar_layer_type_unselected)
        binding.btnRadarSnow.setBackgroundResource(R.drawable.bg_btn_radar_layer_type_unselected)
        binding.btnRadarWind.setBackgroundResource(R.drawable.bg_btn_radar_layer_type_unselected)
        binding.btnRadarPressure.setBackgroundResource(R.drawable.bg_btn_radar_layer_type_unselected)
        binding.btnRadarHumidity.setBackgroundResource(R.drawable.bg_btn_radar_layer_type_unselected)
        binding.btnRadarClouds.setBackgroundResource(R.drawable.bg_btn_radar_layer_type_unselected)

        selectedButton.setBackgroundResource(R.drawable.bg_btn_radar_layer_type_selected)
    }
}