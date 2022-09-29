package com.example.weather.data

data class GetTwelveHoursForecastModel(
    val DateTime: String,
    val WeatherIcon: Int,
    val Temperature: TemperatureDetailModel,
    val Wind: WindModel
)
