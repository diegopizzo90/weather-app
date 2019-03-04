package com.example.weatherapp.ui.utils

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.request.RequestOptions
import com.example.weatherapp.R

fun glideOptions(context: Context): RequestOptions {
    return RequestOptions()
        .centerCrop().placeholder(setProgressBar(context))
        .error(R.drawable.ic_no_image_24dp)
}


private fun setProgressBar(context: Context): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    return circularProgressDrawable
}