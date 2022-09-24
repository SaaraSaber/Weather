package com.example.weather.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.data.ModelRecCityName
import com.example.weather.databinding.ReasultSearchCityFinderBinding

class CityFinderAdapter : RecyclerView.Adapter<CityFinderAdapter.ViewHolder>() {
    var cityEventListener: CityEventListener? = null
    var cityLists = ArrayList<ModelRecCityName>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ReasultSearchCityFinderBinding.bind(view)

        fun bind(modelRecCityName: ModelRecCityName) {
            binding.tvCityName.text = modelRecCityName.LocalizedName
            binding.tvProvinceName.text = modelRecCityName.AdministrativeArea.LocalizedName
            binding.tvCountryName.text = modelRecCityName.Country.LocalizedName
            Log.i("TAG", "bind: " + modelRecCityName.LocalizedName)
            itemView.setOnClickListener {
                cityEventListener?.onClickCityListener(modelRecCityName.Key)
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
    }
}