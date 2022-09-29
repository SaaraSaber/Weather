package com.example.weather.repository.dataSource

import com.example.weather.api.ApiService
import com.example.weather.data.GetTwelveHoursForecastModel
import io.reactivex.rxjava3.core.Single

class TwelveHoursForecastRemoteDataSource(val apiService: ApiService):TwelveHoursForecastDataSource {
    override fun getTwelveHoursForecast(cityId: String): Single<ArrayList<GetTwelveHoursForecastModel>> =
        apiService.getTwelveHoursForecast(cityId)
}