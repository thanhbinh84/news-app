package com.binhnguyen.newsapp.ui.headline

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.binhnguyen.newsapp.databinding.RecyclerViewItemBinding
import com.binhnguyen.newsapp.network.News

class NewsRecyclerAdapter : ListAdapter<News, NewsRecyclerAdapter.NewsViewHolder>(DiffCallback) {
    class NewsViewHolder(private var binding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            binding.news = news
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.url == newItem.url
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsViewHolder {
        return NewsViewHolder(RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(
        holder: NewsViewHolder,
        position: Int
    ) {
        val news = getItem(position)
        holder.bind(news)
    }
}