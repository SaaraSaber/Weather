package com.example.weather.repository.dataSource

import com.example.weather.api.ApiService
import com.example.weather.data.RecCityNameModel
import io.reactivex.rxjava3.core.Single

class CityFinderRemoteDataSource(val apiService: ApiService) : CityFinderDataSource {

    override fun getCityName(cityName: String): Single<ArrayList<RecCityNameModel>> =
        apiService.getCityList(cityName = cityName)
}