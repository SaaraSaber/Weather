package com.example.weather.repository.dataSource

import com.example.weather.api.ApiService
import com.example.weather.data.GetFutureDayForecastModel
import io.reactivex.rxjava3.core.Single

class FiveDayForecastRemoteDataSource(val apiService: ApiService) : FiveDayForecastDataSource {
    override fun getFiveDayForecast(cityId: String): Single<GetFutureDayForecastModel> =
        apiService.getFiveDayForecast(cityId)
}