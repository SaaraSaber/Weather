package com.example.weather.repository

import com.example.weather.data.GetCurrentConditionModel
import com.example.weather.repository.dataSource.WeatherDetailDataSource
import io.reactivex.rxjava3.core.Single

class WeatherDetailRepositoryImpl(val dataSource: WeatherDetailDataSource) :
    WeatherDetailRepository {
    override fun getDetailWeather(cityId:String): Single<ArrayList<GetCurrentConditionModel>> =
        dataSource.getDetailWeather(cityId)

}