package com.example.weather.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.common.ShowLoading
import com.example.weather.data.ModelGetCurrentCondition
import com.example.weather.repository.WeatherDetailRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class WeatherDetailViewModel(val repository: WeatherDetailRepository) : ViewModel() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val liveData: LiveData<ArrayList<ModelGetCurrentCondition>> get() = _LiveData
    private val _LiveData: MutableLiveData<ArrayList<ModelGetCurrentCondition>> = MutableLiveData()
    private lateinit var loading: ShowLoading

    fun sendCurrentConditionApi(context: Context, cityId: String) {
        loading = ShowLoading(context)
        repository.getDetailWeather(cityId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ArrayList<ModelGetCurrentCondition>> {
                override fun onSubscribe(d: Disposable) {
                    loading.dismissDialog()
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: ArrayList<ModelGetCurrentCondition>) {
                    _LiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.e("TAG", e.toString())
                    e.printStackTrace()
                }

            })
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}