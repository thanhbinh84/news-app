package com.binhnguyen.newsapp.ui.custom

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.binhnguyen.newsapp.data.AppPreferences
import com.binhnguyen.newsapp.network.ApiFilter
import com.binhnguyen.newsapp.network.NewsApi
import com.binhnguyen.newsapp.network.NewsSources
import com.binhnguyen.newsapp.ui.news.NewsViewModel
import kotlinx.coroutines.Deferred

class CustomViewModel(app:Application) : NewsViewModel(app) {
    val appPreference = AppPreferences(app)

    private var _keyword = MutableLiveData<String>().apply {
        value = appPreference.getFilterKey()
    }
    val keyword: LiveData<String> = _keyword

    override fun getNewsDeferred(): Deferred<NewsSources> {
        return NewsApi.retrofitService.getCustomNews(_keyword.value!!)
    }

    fun updateFilter(apiFilter: ApiFilter) {
        appPreference.filterKey = apiFilter.value
        _keyword.value = apiFilter.value
        getNews()
    }
}