package com.example.weather.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.data.RecCityNameModel
import com.example.weather.databinding.ReasultSearchCityFinderBinding
import com.example.weather.viewModel.CityFinderViewModel

class CityFinderAdapter : RecyclerView.Adapter<CityFinderAdapter.ViewHolder>() {
    var cityEventListener: CityEventListener? = null
    var cityLists = ArrayList<RecCityNameModel>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ReasultSearchCityFinderBinding.bind(view)

        fun bind(recCityNameModel: RecCityNameModel) {
            binding.tvCityName.text = recCityNameModel.LocalizedName
            binding.tvProvinceName.text = recCityNameModel.AdministrativeArea.LocalizedName
            binding.tvCountryName.text = recCityNameModel.Country.LocalizedName
            Log.i("TAG", "bind: " + recCityNameModel.LocalizedName)
            itemView.setOnClickListener {
                cityEventListener?.onSaveCityId(recCityNameModel.Key)
                cityEventListener?.onClickCityListener(recCityNameModel.Key)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.reasult_search_city_finder, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cityLists[position])
    }

    override fun getItemCount(): Int = cityLists.size

    interface CityEventListener {
        fun onClickCityListener(cityId: String)
        fun onSaveCityId(cityId:String)
    }
}