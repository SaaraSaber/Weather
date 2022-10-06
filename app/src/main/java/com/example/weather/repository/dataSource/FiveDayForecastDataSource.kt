package com.example.weather.repository.dataSource

import com.example.weather.data.GetFutureDayForecastModel
import io.reactivex.rxjava3.core.Single

interface FiveDayForecastDataSource {
    fun getFiveDayForecast(cityId: String): Single<GetFutureDayForecastModel>
}