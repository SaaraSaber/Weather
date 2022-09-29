package com.example.weather.repository.dataSource

import com.example.weather.data.GetTwelveHoursForecastModel
import io.reactivex.rxjava3.core.Single

interface TwelveHoursForecastDataSource {
    fun getTwelveHoursForecast(cityId: String): Single<ArrayList<GetTwelveHoursForecastModel>>
}