package com.example.weather.repository

import com.example.weather.data.GetFutureDayForecastModel
import io.reactivex.rxjava3.core.Single

interface OnDayForecastRepository {
    fun getOnDayForecast(cityId: String): Single<GetFutureDayForecastModel>
}