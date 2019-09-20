package com.binhnguyen.newsapp.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binhnguyen.newsapp.network.News
import com.binhnguyen.newsapp.network.NewsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

enum class ApiStatus { LOADING, ERROR, DONE }
class NewsViewModel : ViewModel() {


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _status = MutableLiveData<ApiStatus>()

    val status: LiveData<ApiStatus>
        get() = _status

    private val _newsList = MutableLiveData<List<News>>()
    val newsList: LiveData<List<News>>
        get() = _newsList

    private val _navigateToSelectedNews = MutableLiveData<News>()

    val navigateToSelectedNews: LiveData<News>
        get() = _navigateToSelectedNews

    init {
        getNews()
    }

    private fun getNews() {
        coroutineScope.launch {
            val getNewsDeferred = NewsApi.retrofitService.getNews()
            try {
                _status.value = ApiStatus.LOADING
                var result = getNewsDeferred.await()
                _newsList.value = result.articles
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _newsList.value = ArrayList()
                println("Failure: " + e.message)
            }
        }
    }

    fun displayNewsDetails(news: News) {
        _navigateToSelectedNews.value = news
    }

    fun displayNewsDetailsComplete() {
        _navigateToSelectedNews.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}