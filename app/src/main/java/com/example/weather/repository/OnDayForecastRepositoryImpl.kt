package com.example.weather.repository

import com.example.weather.data.GetFutureDayForecastModel
import com.example.weather.repository.dataSource.OnDayForecastDataSource
import io.reactivex.rxjava3.core.Single

class OnDayForecastRepositoryImpl(val dataSource: OnDayForecastDataSource) :
    OnDayForecastRepository {
    override fun getOnDayForecast(cityId: String): Single<GetFutureDayForecastModel> =
        dataSource.getOnDayForecast(cityId)

}