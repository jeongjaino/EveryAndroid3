package com.jaino.pagingexample.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun loadImage(view: ImageView, src: String?) {
        if (src != null) {
            Glide.with(view.context)
                .load(src)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .apply(RequestOptions().fitCenter())
                .into(view)
        }
    }
}