package com.example.weather.data

import com.google.gson.annotations.SerializedName

data class DayNightModel(
    @SerializedName("Icon")
    val icon: Int,

    @SerializedName("IconPhrase")
    val iconPhrase: String,

    @SerializedName("ShortPhrase")
    val shortPhrase: String,

    @SerializedName("LongPhrase")
    val longPhrase: String,

    @SerializedName("PrecipitationProbability")
    val precipitationProbability: Int,

    @SerializedName("ThunderstormProbability")
    val thunderstormProbability: Int,

    @SerializedName("RainProbability")
    val rainProbability: Int,

    @SerializedName("SnowProbability")
    val snowProbability: Int,

    @SerializedName("Wind")
    val wind: WindModel,

    @SerializedName("WindGust")
    val windGust: WindGustModel,

    @SerializedName("CloudCover")
    val cloudCover: Int
)
