package com.example.weather.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.weather.common.ShowLoading
import com.example.weather.data.ModelRecCityName
import com.example.weather.repository.CityFinderRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CityFinderViewModel(val repository: CityFinderRepository) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val liveData: LiveData<ArrayList<ModelRecCityName>> get() = _liveData
    private val _liveData: MutableLiveData<ArrayList<ModelRecCityName>> = MutableLiveData()
    private lateinit var loading: ShowLoading

    fun cityFinder(context: Context, cityName: String) {
        loading= ShowLoading(context)
        repository.getCityName(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ArrayList<ModelRecCityName>> {
                override fun onSubscribe(d: Disposable) {
                    loading.dismissDialog()
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: ArrayList<ModelRecCityName>) {
                    _liveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.e("TAG", e.toString())
                }

            })
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}