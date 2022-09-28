package com.example.weather.repository

import com.example.weather.data.GetCurrentConditionModel
import io.reactivex.rxjava3.core.Single

interface WeatherDetailRepository {
    fun getDetailWeather(cityId:String):Single<ArrayList<GetCurrentConditionModel>>
}