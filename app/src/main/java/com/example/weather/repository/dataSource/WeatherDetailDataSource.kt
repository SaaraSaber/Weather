package com.example.weather.repository.dataSource

import com.example.weather.data.ModelGetCurrentCondition
import io.reactivex.rxjava3.core.Single

interface WeatherDetailDataSource {

    fun getDetailWeather(cityId:String):Single<ArrayList<ModelGetCurrentCondition>>
    fun saveWeather(cityId:String)
}