package com.example.weather.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.common.ShowLoading
import com.example.weather.data.GetTwelveHoursForecastModel
import com.example.weather.repository.TwelveHoursForecastRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class TwelveHoursForecastViewModel(val repository: TwelveHoursForecastRepository) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val liveData: LiveData<ArrayList<GetTwelveHoursForecastModel>> get() = _livedata
    private val _livedata: MutableLiveData<ArrayList<GetTwelveHoursForecastModel>> =
        MutableLiveData()
    private lateinit var showLoading: ShowLoading

    fun sendTwelveHoursForecastApi(context: Context, cityId: String) {
        showLoading = ShowLoading(context)
        repository.getTwelveHoursForecast(cityId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ArrayList<GetTwelveHoursForecastModel>> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: ArrayList<GetTwelveHoursForecastModel>) {
                    _livedata.value = t
                    showLoading.dismissDialog()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

            })
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}