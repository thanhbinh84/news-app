package com.binhnguyen.newsapp.ui.headline

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binhnguyen.newsapp.network.News
import com.binhnguyen.newsapp.network.NewsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HeadlineViewModel : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _status = MutableLiveData<String>()

    val status: LiveData<String>
        get() = _status

    private val _newsList = MutableLiveData<List<News>>()
    val newsList: LiveData<List<News>>
        get() = _newsList

    init {
        getNews()
    }

    private fun getNews() {
        coroutineScope.launch {
            var getNewsDeferred = NewsApi.retrofitService.getNews()
            try {
                var result = getNewsDeferred.await()
                if (result.totalResults > 0) {
                    _newsList.value = result.articles
                }
            } catch (e: Exception) {
//                _text.value = "Failure: " + e.message
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}