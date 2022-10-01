package com.example.weather.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.data.GetTwelveHoursForecastModel
import com.example.weather.databinding.LayoutRecTwelveHoursForecastBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TwelveHoursForecastAdapter(private val context: Context) :
    RecyclerView.Adapter<TwelveHoursForecastAdapter.ViewHolder>() {

    var lsTwelveHoursForecastViewModel = ArrayList<GetTwelveHoursForecastModel>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = LayoutRecTwelveHoursForecastBinding.bind(view)

        fun bind(getTwelveHoursForecastModel: GetTwelveHoursForecastModel) {
            binding.tvTime.text = getTwelveHoursForecastModel.DateTime.substring(11,16)
            binding.tvTemperature.text = getTwelveHoursForecastModel.Temperature.Value.toString()
            binding.tvWindSpeed.text = getTwelveHoursForecastModel.Wind.Speed.Value.toString()
            binding.imgWindDirection.rotation =
                getTwelveHoursForecastModel.Wind.Direction.Degrees.toFloat()
            binding.imgTemperatureIcon.setBackgroundResource(
                context.resources.getIdentifier(
                    "s_${getTwelveHoursForecastModel.WeatherIcon}",
                    "drawable",
                    context.packageName
                )
            )
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_rec_twelve_hours_forecast, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lsTwelveHoursForecastViewModel[position])
    }

    override fun getItemCount(): Int = lsTwelveHoursForecastViewModel.size
}