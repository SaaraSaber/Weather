package com.example.weather.repository.dataSource

import com.example.weather.api.ApiService
import com.example.weather.data.GetFutureDayForecastModel
import io.reactivex.rxjava3.core.Single

class OnDayForecastRemoteDataSource(val apiService: ApiService) : OnDayForecastDataSource {
    override fun getOnDayForecast(cityId: String): Single<GetFutureDayForecastModel> =
        apiService.getOnDayForecast(cityId)
}