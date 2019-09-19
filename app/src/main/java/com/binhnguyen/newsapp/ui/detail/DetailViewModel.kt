package com.binhnguyen.newsapp.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.binhnguyen.newsapp.network.News

class DetailViewModel (news: News, app: Application) : AndroidViewModel(app) {
    private val _selectedNews = MutableLiveData<News>()

    val selectedNews: LiveData<News>
        get() = _selectedNews

    init {
        _selectedNews.value = news
    }
}