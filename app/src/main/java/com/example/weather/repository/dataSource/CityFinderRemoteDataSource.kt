package com.example.weather.repository.dataSource

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.weather.CITY_ID
import com.example.weather.api.ApiService
import com.example.weather.data.RecCityNameModel
import io.reactivex.rxjava3.core.Single

class CityFinderRemoteDataSource(
    val apiService: ApiService,
    var encryptedSharedPreferences: SharedPreferences, val context: Context
) : CityFinderDataSource {
    private lateinit var masterKeys: String

    override fun getCityName(cityName: String): Single<ArrayList<RecCityNameModel>> =
        apiService.getCityList(cityName = cityName)

    override fun saveCityId(cityId: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            masterKeys = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
            encryptedSharedPreferences = EncryptedSharedPreferences.create(
                "Data",
                masterKeys,
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } else {
            encryptedSharedPreferences = context.getSharedPreferences(
                "Data",
                Context.MODE_PRIVATE
            )
        }
        val edSharedPreferences = encryptedSharedPreferences.edit()
        edSharedPreferences.putString(CITY_ID, cityId)
        edSharedPreferences.apply()
    }

    override fun readCityId(): String? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            masterKeys = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
            encryptedSharedPreferences = EncryptedSharedPreferences.create(
                "Data",
                masterKeys,
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } else {
            encryptedSharedPreferences = context.getSharedPreferences(
                "Data",
                Context.MODE_PRIVATE
            )
        }
        return encryptedSharedPreferences.getString(CITY_ID,"0")
    }

    override fun cleanCityId() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            masterKeys = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
            encryptedSharedPreferences = EncryptedSharedPreferences.create(
                "Data",
                masterKeys,
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } else {
            encryptedSharedPreferences = context.getSharedPreferences(
                "Data",
                Context.MODE_PRIVATE
            )
        }
        val edSharedPreferences = encryptedSharedPreferences.edit()
        edSharedPreferences.clear()
        edSharedPreferences.apply()
    }
}