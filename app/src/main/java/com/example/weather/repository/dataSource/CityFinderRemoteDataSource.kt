package com.example.weather.repository.dataSource

import com.example.weather.api.ApiService
import com.example.weather.data.ModelRecCityName
import io.reactivex.rxjava3.core.Single

class CityFinderRemoteDataSource(val apiService: ApiService) : CityFinderDataSource {

    override fun getCityName(cityName: String): Single<ArrayList<ModelRecCityName>> =
        apiService.getCityList(cityName = cityName)
}