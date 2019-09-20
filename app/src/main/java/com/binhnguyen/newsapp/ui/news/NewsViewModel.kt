package com.binhnguyen.newsapp.ui.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.binhnguyen.newsapp.network.News
import com.binhnguyen.newsapp.network.NewsApi
import com.binhnguyen.newsapp.network.NewsSources
import kotlinx.coroutines.*
import java.util.*

enum class ApiStatus { LOADING, ERROR, DONE }
open class NewsViewModel(app: Application) : AndroidViewModel(app) {


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

    protected fun getNews() {
        coroutineScope.launch {
            val getNewsDeferred = getNewsDeferred()
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

    open fun getNewsDeferred() :Deferred<NewsSources> {
        return NewsApi.retrofitService.getNews()
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