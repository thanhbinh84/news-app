package com.binhnguyen.newsapp.binding

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.binhnguyen.newsapp.R
import com.binhnguyen.newsapp.network.News
import com.binhnguyen.newsapp.ui.headline.NewsRecyclerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<News>?) {
    val adapter = recyclerView.adapter as NewsRecyclerAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imageView:ImageView, imageUrl: String?) {
    imageUrl?.let {
        var imageUri = imageUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imageUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imageView)

    }
}