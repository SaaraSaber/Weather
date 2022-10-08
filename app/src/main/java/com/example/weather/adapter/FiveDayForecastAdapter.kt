package com.example.weather.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.data.DailyForecastsModel
import com.example.weather.databinding.LayoutRecFiveDayForecastBinding
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class FiveDayForecastAdapter(val context: Context) :
    RecyclerView.Adapter<FiveDayForecastAdapter.ViewHolder>() {

    var lsDailyForecastsModel = ArrayList<DailyForecastsModel>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = LayoutRecFiveDayForecastBinding.bind(view)

        fun bind(dailyForecastsModel: DailyForecastsModel) {
            val calendar: Calendar = Calendar.getInstance()
            calendar.time = convertStringToData(dailyForecastsModel.Date)
            val dayInMonth: Int = calendar.get(Calendar.DAY_OF_MONTH)
            val dayInWeek: Int = calendar.get(Calendar.DAY_OF_WEEK)
            val month: Int = calendar.get(Calendar.MONTH)
            val dayName = getDayName(dayInWeek)

            binding.tvDayName.text = dayName
            binding.tvDate.text = month.toString().plus("/").plus(dayInMonth)
            binding.imgDayIcon.setBackgroundResource(
                context.resources.getIdentifier(
                    "s_${dailyForecastsModel.Day.icon}",
                    "drawable",
                    context.packageName
                )
            )
            binding.tvMaxTemperature.text =
                dailyForecastsModel.Temperature.Maximum.Value.toString().plus("˚")
            binding.tvMinTemperature.text =
                dailyForecastsModel.Temperature.Minimum.Value.toString().plus("˚")
            binding.imgNightIcon.setBackgroundResource(
                context.resources.getIdentifier(
                    "s_${dailyForecastsModel.Night.icon}",
                    "drawable",
                    context.packageName
                )
            )

            val averageWinSpeed = splitWindDirectionWithTwoDecimal(
                getAverageWindSpeed(
                    dailyForecastsModel.Day.wind.Speed.Value,
                    dailyForecastsModel.Night.wind.Speed.Value
                ).toFloat()
            )
            binding.tvWindSpeed.text = averageWinSpeed
            binding.imgWindDirection.rotation = getAverageWindDirection(
                dailyForecastsModel.Day.wind.Direction.Degrees,
                dailyForecastsModel.Night.wind.Direction.Degrees
            )

        }

    }

    private fun convertStringToData(dataInString: String): Date {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val text = dataInString.substring(0, 10)
        Log.d("substringData", text)
        return formatter.parse(text)!!
    }

    private fun getDayName(dayInInt: Int): String {
        when (dayInInt) {
            Calendar.SATURDAY -> return "Sat"
            Calendar.SUNDAY -> return "Sun"
            Calendar.MONDAY -> return "Mon"
            Calendar.TUESDAY -> return "Tue"
            Calendar.WEDNESDAY -> return "Wed"
            Calendar.THURSDAY -> return "The"
            Calendar.FRIDAY -> return "Fri"
        }
        return "invalid day"
    }

    private fun splitWindDirectionWithTwoDecimal(windDirection: Float): String {
        val decimalFormat = DecimalFormat("#.#")
        decimalFormat.roundingMode = RoundingMode.DOWN
        return decimalFormat.format(windDirection)
    }

    private fun getAverageWindSpeed(minSpeed: Float, maxSpeed: Float): String {
        return ((minSpeed + maxSpeed) / 2).toString()
    }

    private fun getAverageWindDirection(dayDegree: Int, nightDegree: Int): Float {
        return ((dayDegree + nightDegree) / 2).toFloat()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_rec_five_day_forecast, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lsDailyForecastsModel[position])
    }

    override fun getItemCount(): Int = lsDailyForecastsModel.size
}
