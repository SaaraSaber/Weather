package com.example.weather.repository

import com.example.weather.data.GetFutureDayForecastModel
import com.example.weather.repository.dataSource.FiveDayForecastDataSource
import io.reactivex.rxjava3.core.Single

class FiveDayForecastRepositoryImpl(val dataSource: FiveDayForecastDataSource) :
    FiveDayForecastRepository {
    override fun getFiveDayForecast(cityId: String): Single<GetFutureDayForecastModel> =
        dataSource.getFiveDayForecast(cityId)

}