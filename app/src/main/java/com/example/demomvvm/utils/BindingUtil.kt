package com.example.demomvvm.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.demomvvm.utils.ext.loadImageWithUrl

object BindingUtil {

    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun setImageUrl(imageView: ImageView, url: String?) {
        url?.let {
            imageView.loadImageWithUrl(it)
        }
    }

    @JvmStatic
    @BindingAdapter("app:adapter")
    fun setAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        recyclerView.adapter = adapter
    }
}
