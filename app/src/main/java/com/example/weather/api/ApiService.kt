package com.example.weather.api

import com.example.weather.API_ID
import com.example.weather.data.ModelGetCurrentCondition
import com.example.weather.data.ModelRecCityName
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("locations/v1/cities/search")
    fun getCityList(
        @Query("apikey") apikey: String = API_ID,
        @Query("q") cityName: String
    ): Single<ArrayList<ModelRecCityName>>

    @GET("currentconditions/v1/{cityId}")
    fun detailWeather(
        @Path("cityId") cityId: String,
        @Query("apikey") apikey: String = API_ID,
        @Query("details") details: Boolean = true
    ): Single<ArrayList<ModelGetCurrentCondition>>

}

fun createApiServiceInstance(): ApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://dataservice.accuweather.com/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(ApiService::class.java)

}