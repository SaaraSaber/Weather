package com.example.weather.repository

import com.example.weather.data.GetTwelveHoursForecastModel
import io.reactivex.rxjava3.core.Single

interface TwelveHoursForecastRepository {
    fun getTwelveHoursForecast(cityId: String): Single<ArrayList<GetTwelveHoursForecastModel>>
}