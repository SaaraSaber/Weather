package com.example.weather

import android.app.Application
import android.content.SharedPreferences
import com.example.weather.api.createApiServiceInstance
import com.example.weather.repository.*
import com.example.weather.repository.dataSource.*
import com.example.weather.viewModel.CityFinderViewModel
import com.example.weather.viewModel.OnDayForecastViewModel
import com.example.weather.viewModel.TwelveHoursForecastViewModel
import com.example.weather.viewModel.WeatherDetailViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val myModules = module {
            single { createApiServiceInstance() }
            factory<CityFinderRepository> { CityFinderRepositoryImp(CityFinderRemoteDataSource(get())) }
            single<SharedPreferences> { this@App.getSharedPreferences("app_setting", MODE_PRIVATE) }
            factory<WeatherDetailRepository> {
                WeatherDetailRepositoryImpl(
                    WeatherDetailLocalDataSource(get(), get())
                )
            }
            factory<OnDayForecastRepository> {
                OnDayForecastRepositoryImpl(OnDayForecastRemoteDataSource(get()))
            }
            factory<TwelveHoursForecastRepository> {
                TwelveHoursForecastRepositoryImpl(
                    TwelveHoursForecastRemoteDataSource(get())
                )
            }
            viewModel { CityFinderViewModel(get()) }
            viewModel { WeatherDetailViewModel(get()) }
            viewModel { OnDayForecastViewModel(get()) }
            viewModel { TwelveHoursForecastViewModel(get()) }
        }
        startKoin {
            androidContext(this@App)
            modules(myModules)
        }
    }
}