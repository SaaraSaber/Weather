package com.example.weather.repository.dataSource

import com.example.weather.data.RecCityNameModel
import io.reactivex.rxjava3.core.Single

interface CityFinderDataSource {

    fun getCityName(cityName: String): Single<ArrayList<RecCityNameModel>>
}