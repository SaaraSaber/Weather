package com.example.weather.viewModel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.*
import com.example.weather.common.ShowLoading
import com.example.weather.data.RecCityNameModel
import com.example.weather.repository.CityFinderRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CityFinderViewModel(val repository: CityFinderRepository) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val liveData: LiveData<ArrayList<RecCityNameModel>> get() = _liveData
    private val _liveData: MutableLiveData<ArrayList<RecCityNameModel>> = MutableLiveData()
    private lateinit var loading: ShowLoading

     fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    fun cityFinder(context: Context, cityName: String) {
        loading = ShowLoading(context)
        repository.getCityName(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ArrayList<RecCityNameModel>> {
                override fun onSubscribe(d: Disposable) {
                    loading.dismissDialog()
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: ArrayList<RecCityNameModel>) {
                    _liveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.e("TAG", e.toString())
                }

            })
    }

    fun saveCityIdFromSharedPreference(cityId: String) {
        repository.saveCityId(cityId)
    }

    val cityId: MutableLiveData<String> = MutableLiveData()
    fun readCityIdFromSharedPreference() {
        cityId.postValue(repository.readCityId())
    }

    fun cleanCityIdFromSharedPreference() {
        repository.cleanCityId()
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}