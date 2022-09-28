package com.example.weather.data

data class GetCurrentConditionModel(
    val LocalObservationDateTime: String,
    val WeatherText: String,
    val WeatherIcon:Int,
    val Temperature:CurrentTemperatureModel,
    val Wind: CurrentWindModel
)
