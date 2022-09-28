package com.example.weather.data

data class RecCityNameModel(

    val Key: String,
    val LocalizedName: String,
    val Country: CountryModel,
    val AdministrativeArea: OstanNameModel
)