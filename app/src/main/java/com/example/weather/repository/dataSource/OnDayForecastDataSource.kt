package com.example.weather.repository.dataSource

import com.example.weather.data.GetFutureDayForecastModel
import io.reactivex.rxjava3.core.Single

interface OnDayForecastDataSource {
    fun getOnDayForecast(cityId: String): Single<GetFutureDayForecastModel>
}