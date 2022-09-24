package com.example.weather.common


import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.example.weather.R

class ShowLoading(context: Context) {

    val dialogLoading: Dialog = Dialog(context)

    init {
        dialogLoading.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogLoading.setCancelable(false)
        dialogLoading.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogLoading.setContentView(R.layout.show_loading_animation)
        dialogLoading.show()
    }

    fun dismissDialog() {
        dialogLoading.dismiss()
    }
}