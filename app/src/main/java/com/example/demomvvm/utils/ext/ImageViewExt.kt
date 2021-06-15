package com.example.demomvvm.utils.ext

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

fun ImageView.loadImageWithUrl(url: String) {
    val circularProgressDrawable = CircularProgressDrawable(context).apply {
        strokeWidth = 5f
        centerRadius = 30f
        start()
    }
    Glide.with(this)
        .load(url)
        .placeholder(circularProgressDrawable)
        .into(this)
}
