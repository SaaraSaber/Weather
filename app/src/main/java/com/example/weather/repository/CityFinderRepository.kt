package com.example.weather.repository

import com.example.weather.data.RecCityNameModel
import io.reactivex.rxjava3.core.Single

interface CityFinderRepository {

    fun getCityName(cityName: String): Single<ArrayList<RecCityNameModel>>
    fun saveCityId(cityId:String)
    fun readCityId():String?
    fun cleanCityId()
}