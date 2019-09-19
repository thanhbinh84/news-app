package com.binhnguyen.newsapp.ui.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.binhnguyen.newsapp.network.News

class DetailViewModelFactory(
    private val news: News,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(news, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}