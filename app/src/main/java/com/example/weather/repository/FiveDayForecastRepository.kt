package com.example.weather.repository

import com.example.weather.data.GetFutureDayForecastModel
import io.reactivex.rxjava3.core.Single

interface FiveDayForecastRepository {
    fun getFiveDayForecast(cityId: String): Single<GetFutureDayForecastModel>
}