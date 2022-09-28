package com.example.weather.data

data class GetFutureDayForecastModel(
    val Headline: HeadlineModel,
    val DailyForecasts: ArrayList<DailyForecastsModel>
)
