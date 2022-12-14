package com.example.weather.repository.dataSource

import android.content.SharedPreferences
import com.example.weather.CITY_ID
import com.example.weather.api.ApiService
import com.example.weather.data.GetCurrentConditionModel
import io.reactivex.rxjava3.core.Single

class WeatherDetailLocalDataSource(val apiService: ApiService) :
    WeatherDetailDataSource {

    override fun getDetailWeather(cityId: String): Single<ArrayList<GetCurrentConditionModel>> =
        apiService.detailWeather(cityId)

}