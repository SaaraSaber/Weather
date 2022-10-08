package com.example.weather.repository.dataSource

import com.example.weather.data.GetCurrentConditionModel
import io.reactivex.rxjava3.core.Single

interface WeatherDetailDataSource {

    fun getDetailWeather(cityId:String):Single<ArrayList<GetCurrentConditionModel>>
}