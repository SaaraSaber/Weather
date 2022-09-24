package com.example.weather.data

data class ModelGetCurrentCondition(
    val LocalObservationDateTime: String,
    val WeatherText: String,
    val WeatherIcon:Int,
    val Temperature:ModelCurrentTemperature,
    val Wind: ModelCurrentWind
)
