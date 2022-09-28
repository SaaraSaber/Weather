package com.example.weather.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.common.ShowLoading
import com.example.weather.data.GetFutureDayForecastModel
import com.example.weather.repository.OnDayForecastRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class OnDayForecastViewModel(val repository: OnDayForecastRepository) : ViewModel() {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val liveData: LiveData<GetFutureDayForecastModel> get() = _liveData
    private val _liveData: MutableLiveData<GetFutureDayForecastModel> = MutableLiveData()
    private lateinit var showLoading: ShowLoading

    fun sendOnDayForecastApi(context: Context, cityId: String) {
        showLoading = ShowLoading(context)
        compositeDisposable.add(
            repository.getOnDayForecast(cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<GetFutureDayForecastModel>() {
                    override fun onSuccess(t: GetFutureDayForecastModel) {
                        _liveData.value = t
                        showLoading.dismissDialog()

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )

    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}