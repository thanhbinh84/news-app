package com.binhnguyen.newsapp.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.binhnguyen.newsapp.databinding.RecyclerViewItemBinding
import com.binhnguyen.newsapp.network.News

class NewsRecyclerAdapter(val onClickListener: OnClickListener) : ListAdapter<News, NewsRecyclerAdapter.NewsViewHolder>(DiffCallback) {
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
        holder.itemView.setOnClickListener {
            onClickListener.onClick(news)
        }
        holder.bind(news)
    }

    class OnClickListener(val clickListener: (news: News) -> Unit) {
        fun onClick(news:News) = clickListener(news)
    }
}