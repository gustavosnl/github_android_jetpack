package com.glima.githubrepolist

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("repositoryImgUrl")
fun repositoryImgUrl(imageView: ImageView, url: String? ) {
    Glide.with(imageView.context)
        .load(url)
//        .apply(RequestOptions()
//            .placeholder(R.drawable.loading_animation)
//            .error(R.drawable.ic_broken_image))ic_broken_image
        .into(imageView)

}