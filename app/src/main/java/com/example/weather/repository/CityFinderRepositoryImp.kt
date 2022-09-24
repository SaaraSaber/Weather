package com.example.weather.repository

import com.example.weather.data.ModelRecCityName
import com.example.weather.repository.dataSource.CityFinderDataSource
import io.reactivex.rxjava3.core.Single

class CityFinderRepositoryImp(val remoteDataSource: CityFinderDataSource) : CityFinderRepository {

    override fun getCityName(cityName: String): Single<ArrayList<ModelRecCityName>> =
        remoteDataSource.getCityName(cityName)
}