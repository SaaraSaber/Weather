package com.example.weather.repository

import com.example.weather.data.GetTwelveHoursForecastModel
import com.example.weather.repository.dataSource.TwelveHoursForecastDataSource
import io.reactivex.rxjava3.core.Single

class TwelveHoursForecastRepositoryImpl(val dataSource: TwelveHoursForecastDataSource) :
    TwelveHoursForecastRepository {
    override fun getTwelveHoursForecast(cityId: String): Single<ArrayList<GetTwelveHoursForecastModel>> =
        dataSource.getTwelveHoursForecast(cityId)
}