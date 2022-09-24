package com.example.weather.repository

import com.example.weather.data.ModelRecCityName
import io.reactivex.rxjava3.core.Single

interface CityFinderRepository {

    fun getCityName(cityName: String): Single<ArrayList<ModelRecCityName>>
}