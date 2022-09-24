package com.example.weather.repository.dataSource

import android.content.SharedPreferences
import com.example.weather.CITY_ID
import com.example.weather.api.ApiService
import com.example.weather.data.ModelGetCurrentCondition
import io.reactivex.rxjava3.core.Single

class WeatherDetailLocalDataSource(
    val sharedPreferences: SharedPreferences,
    val apiService: ApiService
) :
    WeatherDetailDataSource {

    override fun getDetailWeather(cityId: String): Single<ArrayList<ModelGetCurrentCondition>> =
        apiService.detailWeather(cityId)

    override fun saveWeather(cityId: String) {
        sharedPreferences.edit().apply {
            putString(CITY_ID, cityId)
        }
            .apply()
    }
}